package selection_committee.service;

import selection_committee.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto getByEmail(String email);

    UserDto getById(int id);

    UserDto create(UserDto userDto);

    UserDto update(int id, UserDto userDto);

    UserDto changeBlockedStatus(int id);

    void delete(int id);
}
