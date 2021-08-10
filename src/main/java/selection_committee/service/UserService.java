package selection_committee.service;

import selection_committee.dto.UserDto;
import selection_committee.util.CRUDRepository;

public interface UserService extends CRUDRepository<UserDto> {

    UserDto getByEmail(String email);

    UserDto updateByAdmin(int id, UserDto userDto);
}
