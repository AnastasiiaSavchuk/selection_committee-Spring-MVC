package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.UserDto;
import selection_committee.model.User;
import selection_committee.repository.UserRepository;
import selection_committee.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<UserDto> getAll() {
        log.info("getUser list.");
        List<User> users = new ArrayList<>(repository.getAll());
        return mapUserListToUserDtoList(users);
    }

    @Override
    public UserDto getByEmail(String email) {
        log.info("getUser by email : {}.", email);
        User user = repository.getByEmail(email);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto getById(int id) {
        log.info("getUser by id : {}.", id);
        User user = repository.getById(id);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.info("createUser with email : {}.", userDto.getEmail());
        User user = mapUserDtoToUser(userDto);
        user = repository.create(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto update(int id, UserDto userDto) {
        log.info("updateUser with id : {}", id);
        User user = mapUserDtoToUser(userDto);
        user = repository.update(id, user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateByAdmin(int id, UserDto userDto) {
        log.info("updateUserByAdmin with id : {}", id);
        User user = mapUserDtoToUser(userDto);
        user = repository.updateByAdmin(id, user);
        return mapUserToUserDto(user);
    }

    @Override
    public void delete(int id) {
        log.info("deleteUser with id : {}.", id);
        repository.delete(id);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .region(user.getRegion())
                .schoolName(user.getSchoolName())
                .isBlocked(user.isBlocked())
                .build();
    }

    private List<UserDto> mapUserListToUserDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .firstName(user.getFirstName())
                    .middleName(user.getMiddleName())
                    .lastName(user.getLastName())
                    .city(user.getCity())
                    .region(user.getRegion())
                    .schoolName(user.getSchoolName())
                    .isBlocked(user.isBlocked())
                    .build());
        }
        return userDtoList;
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .lastName(userDto.getLastName())
                .city(userDto.getCity())
                .region(userDto.getRegion())
                .schoolName(userDto.getSchoolName())
                .isBlocked(userDto.isBlocked())
                .build();
    }
}
