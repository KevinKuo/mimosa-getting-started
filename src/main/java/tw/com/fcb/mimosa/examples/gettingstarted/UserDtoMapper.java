package tw.com.fcb.mimosa.examples.gettingstarted;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserDtoMapper {
	//如果欄位名稱不一樣, 可以透過Mapping設定定義來源跟目的
	@Mapping(source = "name", target = "userName")
	UserDto from(User user);
	
	@Mapping(source = "userName", target = "name")
	User to(CreateUserDto user);
	
	@Mapping(source = "userName", target = "name")
	User to(ReplaceUserDto user);
	
	User createUser(CreateUserDto user);
	
	@Mapping(source = "userName", target = "name")
	void copyUser(ReplaceUserDto source, @MappingTarget User target);
}
