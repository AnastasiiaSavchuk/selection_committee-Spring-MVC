package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.ApplicationDto;
import selection_committee.exception.ApplicationAlreadyExistsException;
import selection_committee.exception.ApplicationListNotFoundException;
import selection_committee.exception.ApplicationNotFoundException;
import selection_committee.mapper.ApplicationMapper;
import selection_committee.model.*;
import selection_committee.repository.*;
import selection_committee.service.impl.ApplicationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static selection_committee.test.util.TestApplicationUtil.*;
import static selection_committee.test.util.TestFacultySubjectUtil.createFacultySubject;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestFacultyUtil.createFaculty;
import static selection_committee.test.util.TestGradeUtil.createGrade;
import static selection_committee.test.util.TestUserUtil.USER_ID;
import static selection_committee.test.util.TestUserUtil.createUser;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Spy
    private final ApplicationMapper MAPPER = ApplicationMapper.INSTANCE;
    @InjectMocks
    private ApplicationServiceImpl service;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private FacultySubjectRepository facultySubjectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private GradeRepository gradeRepository;

    @Test
    @DisplayName("Test for service : find all 'applications'")
    void getAllTest() {
        List<Application> testList = new ArrayList<>();
        testList.add(createApplication());
        when(applicationRepository.findAll()).thenReturn(testList);

        List<ApplicationDto> testDtoList = service.getAll();
        assertThat(testDtoList.get(0), allOf(
                hasProperty("user", equalTo(testList.get(0).getUser())),
                hasProperty("faculty", equalTo(testList.get(0).getFaculty())),
                hasProperty("sumOfGrades", equalTo(testList.get(0).getSumOfGrades())),
                hasProperty("averageGrade", equalTo(testList.get(0).getAverageGrade())),
                hasProperty("applicationStatus", equalTo(testList.get(0).getApplicationStatus()))
        ));
    }

    @Test
    @DisplayName("Test for service : find all 'applications' by 'faculty'")
    void getAllByFacultyIdTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        List<Application> testList = new ArrayList<>();
        testList.add(createApplication());
        when(applicationRepository.findAllByFaculty(faculty)).thenReturn(testList);

        List<ApplicationDto> testDtoList = service.getAllByFacultyId(FACULTY_ID);
        assertThat(testDtoList.get(0), allOf(
                hasProperty("user", equalTo(testList.get(0).getUser())),
                hasProperty("faculty", equalTo(testList.get(0).getFaculty())),
                hasProperty("sumOfGrades", equalTo(testList.get(0).getSumOfGrades())),
                hasProperty("averageGrade", equalTo(testList.get(0).getAverageGrade())),
                hasProperty("applicationStatus", equalTo(testList.get(0).getApplicationStatus()))
        ));
    }

    @Test
    @DisplayName("Test for service : find all 'applications' by 'user'")
    void getAllGradesByUserIdTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        List<Application> testList = new ArrayList<>();
        testList.add(createApplication());
        when(applicationRepository.findAllByUser(user)).thenReturn(testList);

        List<ApplicationDto> testDtoList = service.getAllByUserId(USER_ID);
        assertThat(testDtoList.get(0), allOf(
                hasProperty("user", equalTo(testList.get(0).getUser())),
                hasProperty("faculty", equalTo(testList.get(0).getFaculty())),
                hasProperty("sumOfGrades", equalTo(testList.get(0).getSumOfGrades())),
                hasProperty("averageGrade", equalTo(testList.get(0).getAverageGrade())),
                hasProperty("applicationStatus", equalTo(testList.get(0).getApplicationStatus()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'application' by 'id'")
    void getByIdTest() {
        Application test = createApplication();
        when(applicationRepository.findById(APPLICATION_ID)).thenReturn(Optional.of(test));

        assertThat(service.getById(APPLICATION_ID), allOf(
                hasProperty("user", equalTo(test.getUser())),
                hasProperty("faculty", equalTo(test.getFaculty())),
                hasProperty("sumOfGrades", equalTo(test.getSumOfGrades())),
                hasProperty("averageGrade", equalTo(test.getAverageGrade())),
                hasProperty("applicationStatus", equalTo(test.getApplicationStatus()))
        ));
    }

    @Test
    @DisplayName("Test for service : create 'application'")
    void createTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Application test = createApplication();
        when(applicationRepository.save(any())).thenReturn(test);

        List<Grade> gradeList = new ArrayList<>();
        gradeList.add(createGrade());
        when(gradeRepository.findAllByUser(user)).thenReturn(gradeList);

        List<FacultySubject> facultySubjectList = new ArrayList<>();
        facultySubjectList.add(createFacultySubject());
        when(facultySubjectRepository.findAllByFaculty(faculty)).thenReturn(facultySubjectList);

        assertThat(service.create(USER_ID, FACULTY_ID), allOf(
                hasProperty("user", equalTo(test.getUser())),
                hasProperty("faculty", equalTo(test.getFaculty())),
                hasProperty("sumOfGrades", equalTo(test.getSumOfGrades())),
                hasProperty("averageGrade", equalTo(test.getAverageGrade())),
                hasProperty("applicationStatus", equalTo(test.getApplicationStatus()))
        ));
    }

    @Test
    @DisplayName("Test for service : delete 'application'")
    void deleteTest() {
        Application test = createApplication();
        when(applicationRepository.findById(APPLICATION_ID)).thenReturn(Optional.of(test));

        service.delete(APPLICATION_ID);
        verify(applicationRepository, times(1)).delete(test);
    }

    @Test
    @DisplayName("Test for service : create 'application' with exception")
    void applicationAlreadyExistsTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        ApplicationDto testDto = createApplicationDto();
        when(applicationRepository.existsByUserAndFaculty(testDto.getUser(), testDto.getFaculty())).thenReturn(true);

        assertThrows(ApplicationAlreadyExistsException.class,
                () -> service.create(USER_ID, FACULTY_ID));
    }

    @Test
    @DisplayName("Test for service : find all 'applications' with exception")
    void applicationListNotFoundTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        assertThrows(ApplicationListNotFoundException.class, () -> service.getAll());
        assertThrows(ApplicationListNotFoundException.class, () -> service.getAllByFacultyId(FACULTY_ID));
        assertThrows(ApplicationListNotFoundException.class, () -> service.getAllByUserId(USER_ID));
    }

    @Test
    @DisplayName("Test for service : find 'application' by 'id' with exception")
    void byIdApplicationNotFoundTest() {
        when(applicationRepository.findById(APPLICATION_ID)).thenReturn(Optional.empty());
        assertThrows(ApplicationNotFoundException.class, () -> service.getById(APPLICATION_ID));
    }
}
