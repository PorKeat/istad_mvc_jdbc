package mapper;


import model.dto.UserCreateDto;
import model.dto.UserResponseDto;
import model.entities.User;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public static UserResponseDto mapFromUserToUserResponseDto(User user){
        return new UserResponseDto(user.getUserName(), user.getEmail(),user.getUuid(), user.getCreatedDate());
    }
    public static User mapFromUserCreateDtoToUser(UserCreateDto userCreateDto){
        return new User(new Random().nextInt(999999999),
                userCreateDto.user_name(),
                userCreateDto.email(),
                UUID.randomUUID().toString(),
                userCreateDto.password(),
                userCreateDto.created_date());
    }
}
