package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;
import tw.com.fcb.mimosa.tracing.Traced;

//宣告代表要追蹤所有的public method, 也可以宣告在個別的method上(可以用在單體式架構上 singleton), 也可以宣告
// var tc = traced()....
@Traced
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/users")
public class UserController {

//	@Autowired
	
	final UserDtoMapper mapper; // 使用lib自動幫你做mapper
	final UserService userService;

	static List<User> users = new ArrayList<User>();

//	@GetMapping("/{name}")
	@GetMapping
//	User getUser(@PathVariable("name") String name) {
	APIResponse<List<UserDto>> getUser() {
//		User user = new User();
//		user.setName(name);
//		user.setAge(18);

//		return User.builder().age(18).name("KK").build();

//		//java 8 以前的寫法
//		List<UserDto> userDtos = new ArrayList<UserDto>();
//		for(User user : users) {
//			userDtos.add(UserDto.builder().name(user.getName()).build());
//		}
//		
//		//java 8以後的寫法
//		List<UserDto> java8UserDtos = users.stream().map(user -> UserDto.builder().name(user.getName()).build())
//		.collect(Collectors.toList());
//		
//		//另外的寫法, 透過第三方套件BeanUtils
//		BeanUtils.copyProperties(users, userDtos);
		
//		for (User user : users) {
//			userDtos.add(mapper.from(user));
//		}
		
		//return userService.getUsers().stream().map(mapper:: from).collect(Collectors.toList());
		List<UserDto> found = userService.getUsers().stream()
		//.map( (user) -> 
//		{
//			UserDto dto = mapper.from(user);
//			return dto;
//		} 
		.map(mapper :: from
		//寫法二，可以省略很多不必要的變數
		).collect(Collectors.toList());

		if(found.isEmpty()) {
			return APIResponse.error(err-> err.code("ERR1").message("data not found"));
		}
		
		return APIResponse.success(userService.getUsers().stream().map(mapper:: from).collect(Collectors.toList()));
		
		//userService.getUsers().stream().map(user -> mapper.from(user)).collect(Collectors.toList());
		//return userDtos;
	}
	
	UserDto myMapper(User user) {
		UserDto dto = mapper.from(user);
		return dto;
	}

	@PostMapping("/names")
	APIResponse<User> findByName(@RequestBody APIRequest<String> name){
		return APIResponse.success(userService.findByName(name.getBody()));
	}
	
	
	@PostMapping
	APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> userDto) {
//		users.add(user);
//		User user = mapper.to(userDto);
		
		return APIResponse.success(userService.createUser(userDto.getBody())).map(User :: getId);
		//createUser會回傳User物件, 可以用map function 指定class跟對應的方法 map(User :: getId)
	}

	// homework

	@PutMapping(" /{id}")
	void replaceUser(@Min(0) @PathVariable Long id, @Validated @RequestBody ReplaceUserDto userDto) {
//		if(user != null) {
//			for(User item : users) {
//				if(item.getId() == id) {
//					item.setName(user.getName());
//					item.setAge(user.getAge());
//				}
//			}
//		}
//		User user = mapper.to(userDto);
		
		User updatedUser = userService.replaceUser(id, userDto);
		System.out.println(updatedUser);
	}

	// deleteUser
	@DeleteMapping("/{id}")
	void deleteUser(@Min(0) @PathVariable("id") Long id) {
//		users.removeIf(user -> user.getId() == id);
		userService.deleteUser(id);
	}
}
