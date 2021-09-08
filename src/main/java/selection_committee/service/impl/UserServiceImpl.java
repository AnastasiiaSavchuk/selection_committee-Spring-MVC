package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import selection_committee.dto.UserDto;
import selection_committee.exception.PasswordsNotValidException;
import selection_committee.exception.UserAlreadyExistsException;
import selection_committee.exception.UserListNotFoundException;
import selection_committee.exception.UserNotFoundException;
import selection_committee.mapper.UserMapper;
import selection_committee.model.Application;
import selection_committee.model.User;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.ApplicationRepository;
import selection_committee.repository.UserRepository;
import selection_committee.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository UR;
    private final ApplicationRepository AR;
    private final UserMapper MAPPER = UserMapper.INSTANCE;

    @Override
    public List<UserDto> getAll() {
        List<User> list = UR.findAll();
        if (list.isEmpty()) {
            throw new UserListNotFoundException();
        }
        log.info("List of 'users' is found.");
        return MAPPER.mapToListDto(list);
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = UR.findByEmail(email).orElseThrow(UserNotFoundException::new);
        log.info("'User' by email : {} is found.", email);
        return MAPPER.mapToUserDto(user);
    }

    @Override
    public UserDto getById(int id) {
        User user = UR.findById(id).orElseThrow(UserNotFoundException::new);
        log.info("'User' by id : {} is found.", id);
        return MAPPER.mapToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        if (UR.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        validationPassword(userDto);
        User user = MAPPER.mapToUser(userDto);
        user = UR.save(user);
        log.info("'User' with email : {} successfully created. ", user.getEmail());
        return MAPPER.mapToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto update(int id, UserDto userDto) {
        User user = MAPPER.mapToUser(userDto);

        User updated = UR.findById(id).orElseThrow(UserNotFoundException::new);
        user.setId(updated.getId());
        user.setEmail(updated.getEmail());
        user.setPassword(updated.getPassword());

        user = UR.save(user);
        log.info("'User' with id : {} successfully updated.", user.getId());
        return MAPPER.mapToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto changeBlockedStatus(int id) {
        User user = UR.findById(id).orElseThrow(UserNotFoundException::new);
        user.setBlockedStatus(!user.isBlockedStatus());
        user = UR.save(user);
        for (Application application : AR.findAllByUser(user)) {
            application.setApplicationStatus(ApplicationStatus.BLOCKED);
            AR.save(application);
        }
        log.info("'User' with id : {} successfully updated blocked status.", user.getId());
        return MAPPER.mapToUserDto(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = UR.findById(id).orElseThrow(UserNotFoundException::new);
        UR.delete(user);
        log.info("'User' with id : {} successfully deleted.", user.getId());
    }

    private void validationPassword(UserDto userDto) {
        if (!StringUtils.isNotBlank(userDto.getPassword()) && !StringUtils.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
            throw new PasswordsNotValidException();
        }
    }
}
