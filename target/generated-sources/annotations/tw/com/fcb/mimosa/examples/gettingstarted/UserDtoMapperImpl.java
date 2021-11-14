package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tw.com.fcb.mimosa.examples.gettingstarted.User.UserBuilder;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto.UserDtoBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.0.v20210708-0430, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto from(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.userName( user.getName() );
        userDto.age( user.getAge() );

        return userDto.build();
    }

    @Override
    public User to(CreateUserDto user) {
        if ( user == null ) {
            return null;
        }

        UserBuilder user1 = User.builder();

        user1.name( user.getUserName() );
        user1.age( user.getAge() );

        return user1.build();
    }

    @Override
    public User to(ReplaceUserDto user) {
        if ( user == null ) {
            return null;
        }

        UserBuilder user1 = User.builder();

        user1.name( user.getUserName() );

        return user1.build();
    }

    @Override
    public User createUser(CreateUserDto user) {
        if ( user == null ) {
            return null;
        }

        UserBuilder user1 = User.builder();

        user1.age( user.getAge() );

        return user1.build();
    }

    @Override
    public void copyUser(ReplaceUserDto source, User target) {
        if ( source == null ) {
            return;
        }

        target.setName( source.getUserName() );
    }
}
