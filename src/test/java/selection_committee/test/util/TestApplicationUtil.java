package selection_committee.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import selection_committee.dto.ApplicationDto;
import selection_committee.model.Application;
import selection_committee.model.Faculty;
import selection_committee.model.User;
import selection_committee.model.enums.ApplicationStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestApplicationUtil {

    public static final int APPLICATION_ID = 1;
    private static final User USER = TestUserUtil.createUser();
    private static final Faculty FACULTY = TestFacultyUtil.createFaculty();

    public static Application createApplication() {
        return Application.builder()
                .id(APPLICATION_ID)
                .user(USER)
                .faculty(FACULTY)
                .applicationStatus(ApplicationStatus.IN_PROCESSING)
                .build();
    }

    public static ApplicationDto createApplicationDto() {
        return ApplicationDto.builder()
                .id(APPLICATION_ID)
                .user(USER)
                .faculty(FACULTY)
                .applicationStatus(ApplicationStatus.IN_PROCESSING)
                .build();
    }
}
