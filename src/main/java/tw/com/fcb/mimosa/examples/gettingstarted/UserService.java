package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorException;
import tw.com.fcb.mimosa.http.APIErrorT9nException;
import tw.com.fcb.mimosa.tracing.Traced;

@Traced
@RequiredArgsConstructor
@Service
public class UserService {

	//bean跟bean可以透過autowired互相調用
	
	
	//寫法一 @Autowired
//	UserRepository userRepository;
	
	//寫法二
//	public UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
	
	final UserDtoMapper mapper; 
	
	//寫法三: 利用lombok宣號@RequiredArgsConstructor 自動注入
	final UserRepository userRepository;
	
	
	public Collection<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(CreateUserDto user){
		return userRepository.save(mapper.to(user));
	}
	
	public User replaceUser(Long id, ReplaceUserDto user){
		
	return userRepository.findById(id).map(db -> {
		mapper.copyUser(user, db);
		return db;
	}).map(userRepository :: save)
			.orElseThrow(() -> new IllegalArgumentException("id[" + id + "] not exist"));
//		return userRepository.findById(id)
//				.map(db -> {
//			db.setName(user.getName());
//			db.setAge(user.getAge());
//			return db;
//		}).map(userRepository :: save)
//		.orElseThrow(() -> new IllegalArgumentException("id[" + id + "] not exist"));	
	
	}
	
	public void deleteUser(Long id){
		userRepository.deleteById(id);
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name)
				//.orElseThrow(() -> new APIErrorException(err -> err.code("ERR1").message("name not found")));
				.orElseThrow(() -> {	
				return new APIErrorT9nException(err  -> err.term(MyErrorCode.NAME_NOT_FOUND));
				});
		//可以自定義error code & error message
		
	}
}
