package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.UserDto;
import selection_committee.exception.UserAlreadyExistsException;
import selection_committee.exception.UserListNotFoundException;
import selection_committee.exception.UserNotFoundException;
import selection_committee.mapper.UserMapper;
import selection_committee.model.Application;
import selection_committee.model.User;
import selection_committee.repository.ApplicationRepository;
import selection_committee.repository.UserRepository;
import selection_committee.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static selection_committee.test.util.TestApplicationUtil.createApplication;
import static selection_committee.test.util.TestUserUtil.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Spy
    private final UserMapper MAPPER = UserMapper.INSTANCE;
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ApplicationRepository applicationRepository;

    @Test
    @DisplayName("Test for service : find all 'users'")
    void getAllTest() {
        List<User> testList = new ArrayList<>();
        testList.add(createUser());
        when(userRepository.findAll()).thenReturn(testList);

        List<UserDto> testDtoList = service.getAll();
        assertThat(testDtoList.get(0), allOf(
                hasProperty("email", equalTo(testList.get(0).getEmail())),
                hasProperty("firstName", equalTo(testList.get(0).getFirstName())),
                hasProperty("middleName", equalTo(testList.get(0).getMiddleName())),
                hasProperty("lastName", equalTo(testList.get(0).getLastName())),
                hasProperty("city", equalTo(testList.get(0).getCity())),
                hasProperty("schoolName", equalTo(testList.get(0).getSchoolName()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'user' by 'email'")
    void getByEmailTest() {
        User test = createUser();
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(test));

        assertThat(service.getByEmail(EMAIL), allOf(
                hasProperty("email", equalTo(test.getEmail())),
                hasProperty("firstName", equalTo(test.getFirstName())),
                hasProperty("middleName", equalTo(test.getMiddleName())),
                hasProperty("lastName", equalTo(test.getLastName())),
                hasProperty("city", equalTo(test.getCity())),
                hasProperty("schoolName", equalTo(test.getSchoolName()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'user' by 'id'")
    void getByIdTest() {
        User test = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(test));

        assertThat(service.getById(USER_ID), allOf(
                hasProperty("email", equalTo(test.getEmail())),
                hasProperty("firstName", equalTo(test.getFirstName())),
                hasProperty("middleName", equalTo(test.getMiddleName())),
                hasProperty("lastName", equalTo(test.getLastName())),
                hasProperty("city", equalTo(test.getCity())),
                hasProperty("schoolName", equalTo(test.getSchoolName()))
        ));
    }

    @Test
    @DisplayName("Test for service : create 'user'")
    void createTest() {
        User test = createUser();
        when(userRepository.save(any())).thenReturn(test);

        UserDto testDto = createUserDto();
        assertThat(service.create(testDto), allOf(
                hasProperty("email", equalTo(test.getEmail())),
                hasProperty("firstName", equalTo(test.getFirstName())),
                hasProperty("middleName", equalTo(test.getMiddleName())),
                hasProperty("lastName", equalTo(test.getLastName())),
                hasProperty("city", equalTo(test.getCity())),
                hasProperty("schoolName", equalTo(test.getSchoolName()))
        ));
    }

    @Test
    @DisplayName("Test for service : update 'user'")
    void updateTest() {
        User test = createUser();
        UserDto testDto = createUserDto();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(test));
        when(userRepository.save(any())).thenReturn(test);

        assertThat(service.update(USER_ID, testDto), allOf(
                hasProperty("email", equalTo(test.getEmail())),
                hasProperty("firstName", equalTo(test.getFirstName())),
                hasProperty("middleName", equalTo(test.getMiddleName())),
                hasProperty("lastName", equalTo(test.getLastName())),
                hasProperty("city", equalTo(test.getCity())),
                hasProperty("schoolName", equalTo(test.getSchoolName()))
        ));
    }

    @Test
    @DisplayName("Test for service : change user's 'blocked status'")
    void changeBlockedStatusTest() {
        User test = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(test));
        when(userRepository.save(any())).thenReturn(test);

        List<Application> applicationList = new ArrayList<>();
        applicationList.add(createApplication());
        when(applicationRepository.findAllByUser(test)).thenReturn(applicationList);

        assertThat(service.changeBlockedStatus(test.getId()), allOf(
                hasProperty("blockedStatus", equalTo(test.isBlockedStatus()))
        ));
    }

    @Test
    @DisplayName("Test for service : delete 'user'")
    void deleteTest() {
        User test = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(test));

        service.delete(test.getId());
        verify(userRepository, times(1)).delete(test);
    }

    @Test
    @DisplayName("Test for service : find all 'users' with exception")
    void userListNotFoundTest() {
        assertThrows(UserListNotFoundException.class, () -> service.getAll());
    }

    @Test
    @DisplayName("Test for service : create 'user' with exception")
    void userAlreadyExistsTest() {
        UserDto testDto = createUserDto();
        when(userRepository.existsByEmail(EMAIL)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> service.create(testDto));
    }

    @Test
    @DisplayName("Test for service : find 'user' by 'email' with exception")
    void byEmailUserNotFoundTest() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.getByEmail(EMAIL));
    }

    @Test
    @DisplayName("Test for service : find 'user' by 'id' with exception")
    void byIdUserNotFoundTest() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.getById(USER_ID));
    }
}
