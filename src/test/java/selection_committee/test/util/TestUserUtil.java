package selection_committee.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import selection_committee.dto.UserDto;
import selection_committee.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUserUtil {

    public static final int USER_ID = 1;
    public static final String EMAIL = "test_email@email.com";
    private static final String PASSWORD = "Password8";
    private static final String FIRST_NAME = "Test_FirstName";
    private static final String MIDDLE_NAME = "Test_MiddleName";
    private static final String LAST_NAME = "Test_LastName";
    private static final String CITY = "test_city";
    private static final String SCHOOL_NAME = "Test_SchoolName";

    public static User createUser() {
        return User.builder()
                .id(USER_ID)
                .email(EMAIL)
                .password(PASSWORD)
                .firstName(FIRST_NAME)
                .middleName(MIDDLE_NAME)
                .lastName(LAST_NAME)
                .city(CITY)
                .schoolName(SCHOOL_NAME)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .id(USER_ID)
                .email(EMAIL)
                .password(PASSWORD)
                .confirmPassword(PASSWORD)
                .firstName(FIRST_NAME)
                .middleName(MIDDLE_NAME)
                .lastName(LAST_NAME)
                .city(CITY)
                .schoolName(SCHOOL_NAME)
                .build();
    }
}
