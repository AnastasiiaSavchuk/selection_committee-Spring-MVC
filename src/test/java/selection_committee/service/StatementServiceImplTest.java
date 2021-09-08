package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.ApplicationDto;
import selection_committee.exception.StatementAlreadyCreateException;
import selection_committee.exception.StatementAlreadyRollbackException;
import selection_committee.model.Application;
import selection_committee.model.Faculty;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.ApplicationRepository;
import selection_committee.repository.FacultyRepository;
import selection_committee.service.impl.StatementServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static selection_committee.test.util.TestApplicationUtil.createApplication;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestFacultyUtil.createFaculty;

@ExtendWith(MockitoExtension.class)
class StatementServiceImplTest {

    @InjectMocks
    private StatementServiceImpl service;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @Test
    @DisplayName("Test for service : create 'statement'")
    void createTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Application app1 = createApplication();

        Application app2 = createApplication();
        app2.setId(2);
        app2.setAverageGrade(141);

        Application app3 = createApplication();
        app3.setId(3);
        app3.setAverageGrade(142);

        Application app4 = createApplication();
        app4.setId(4);
        app4.setAverageGrade(143);

        Application app5 = createApplication();
        app5.setId(5);
        app5.setAverageGrade(139);

        Application app6 = createApplication();
        app6.setId(6);
        app6.setApplicationStatus(ApplicationStatus.BLOCKED);

        List<Application> testList = new ArrayList<>(Arrays.asList(app1, app2, app3, app4, app5, app6));
        when(applicationRepository.findAllByFaculty(faculty)).thenReturn(testList);

        List<ApplicationDto> dtoList = service.create(FACULTY_ID);

        assertThat(dtoList.get(0).getApplicationStatus(), equalTo(testList.get(0).getApplicationStatus()));
        assertThat(dtoList.get(1).getApplicationStatus(), equalTo(testList.get(1).getApplicationStatus()));
        assertThat(dtoList.get(2).getApplicationStatus(), equalTo(testList.get(2).getApplicationStatus()));
        assertThat(dtoList.get(3).getApplicationStatus(), equalTo(testList.get(3).getApplicationStatus()));
        assertThat(dtoList.get(4).getApplicationStatus(), equalTo(testList.get(4).getApplicationStatus()));
    }

    @Test
    @DisplayName("Test for service : rollback 'statement'")
    void rollbackTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Application app1 = createApplication();
        app1.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);

        Application app2 = createApplication();
        app2.setId(2);
        app2.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);

        Application app3 = createApplication();
        app3.setId(3);
        app3.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);

        Application app4 = createApplication();
        app4.setId(4);
        app4.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);

        Application app5 = createApplication();
        app5.setId(5);
        app5.setApplicationStatus(ApplicationStatus.REJECTED);

        Application app6 = createApplication();
        app6.setId(6);
        app6.setApplicationStatus(ApplicationStatus.BLOCKED);

        List<Application> testList = new ArrayList<>(Arrays.asList(app1, app2, app3, app4, app5, app6));
        when(applicationRepository.findAllByFaculty(faculty)).thenReturn(testList);

        List<ApplicationDto> dtoList = service.rollback(FACULTY_ID);
        assertThat(dtoList.get(0).getApplicationStatus(), equalTo(testList.get(0).getApplicationStatus()));
        assertThat(dtoList.get(1).getApplicationStatus(), equalTo(testList.get(1).getApplicationStatus()));
        assertThat(dtoList.get(2).getApplicationStatus(), equalTo(testList.get(2).getApplicationStatus()));
        assertThat(dtoList.get(3).getApplicationStatus(), equalTo(testList.get(3).getApplicationStatus()));
        assertThat(dtoList.get(4).getApplicationStatus(), equalTo(testList.get(4).getApplicationStatus()));
    }

    @Test
    @DisplayName("Test for service : create 'statement' with exception")
    void createStatementAlreadyCreateTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Application app1 = createApplication();
        app1.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);

        Application app2 = createApplication();
        app2.setId(2);
        app2.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);

        Application app3 = createApplication();
        app3.setId(3);
        app3.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);

        Application app4 = createApplication();
        app4.setId(4);
        app4.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);

        Application app5 = createApplication();
        app5.setId(5);
        app5.setApplicationStatus(ApplicationStatus.REJECTED);

        List<Application> testList = new ArrayList<>(Arrays.asList(app1, app2, app3, app4, app5));
        when(applicationRepository.findAllByFaculty(faculty)).thenReturn(testList);

        assertThrows(StatementAlreadyCreateException.class, () -> service.create(FACULTY_ID));
    }

    @Test
    @DisplayName("Test for service : rollback 'statement' with exception")
    void createStatementAlreadyRollbackTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Application app1 = createApplication();

        Application app2 = createApplication();
        app2.setId(2);

        Application app3 = createApplication();
        app3.setId(3);

        Application app4 = createApplication();
        app4.setId(4);

        Application app5 = createApplication();
        app5.setId(5);

        List<Application> testList = new ArrayList<>(Arrays.asList(app1, app2, app3, app4, app5));
        when(applicationRepository.findAllByFaculty(faculty)).thenReturn(testList);

        assertThrows(StatementAlreadyRollbackException.class, () -> service.rollback(FACULTY_ID));
    }
}
