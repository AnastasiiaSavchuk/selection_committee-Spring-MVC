package selection_committee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import selection_committee.dto.UserDto;
import selection_committee.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToUser(UserDto dto);

    UserDto mapToUserDto(User user);

    List<UserDto> mapToListDto(List<User> list);
}
