package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.FacultyDto;
import selection_committee.exception.FacultyAlreadyExistsException;
import selection_committee.exception.FacultyListNotFoundException;
import selection_committee.exception.FacultyNotFoundException;
import selection_committee.mapper.FacultyMapper;
import selection_committee.model.Faculty;
import selection_committee.repository.FacultyRepository;
import selection_committee.service.impl.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static selection_committee.test.util.TestFacultyUtil.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @Spy
    private final FacultyMapper MAPPER = FacultyMapper.INSTANCE;
    @InjectMocks
    private FacultyServiceImpl service;
    @Mock
    private FacultyRepository facultyRepository;

    @Test
    @DisplayName("Test for service : find all 'faculties'")
    void getAllTest() {
        List<Faculty> testList = new ArrayList<>();
        testList.add(createFaculty());
        when(facultyRepository.findAll()).thenReturn(testList);

        List<FacultyDto> testDtoList = service.getAll();
        assertThat(testDtoList.get(0), allOf(
                hasProperty("totalQty", equalTo(testList.get(0).getTotalQty())),
                hasProperty("budgetQty", equalTo(testList.get(0).getBudgetQty())),
                hasProperty("averagePassingGrade", equalTo(testList.get(0).getAveragePassingGrade())),
                hasProperty("budgetQty", equalTo(testList.get(0).getBudgetQty()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'faculty' by 'id'")
    void getByIdTest() {
        Faculty test = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(test));

        assertThat(service.getById(FACULTY_ID), allOf(
                hasProperty("totalQty", equalTo(test.getTotalQty())),
                hasProperty("budgetQty", equalTo(test.getBudgetQty())),
                hasProperty("averagePassingGrade", equalTo(test.getAveragePassingGrade())),
                hasProperty("budgetQty", equalTo(test.getBudgetQty()))
        ));
    }

    @Test
    @DisplayName("Test for service : create 'faculty'")
    void createTest() {
        Faculty test = createFaculty();
        when(facultyRepository.save(any())).thenReturn(test);

        FacultyDto testDto = createFacultyDto();
        assertThat(service.create(testDto), allOf(
                hasProperty("totalQty", equalTo(test.getTotalQty())),
                hasProperty("budgetQty", equalTo(test.getBudgetQty())),
                hasProperty("averagePassingGrade", equalTo(test.getAveragePassingGrade())),
                hasProperty("budgetQty", equalTo(test.getBudgetQty()))
        ));
    }

    @Test
    @DisplayName("Test for service : update 'faculty'")
    void updateTest() {
        Faculty test = createFaculty();
        when(facultyRepository.save(any())).thenReturn(test);

        FacultyDto testDto = createFacultyDto();
        assertThat(service.update(FACULTY_ID, testDto), allOf(
                hasProperty("totalQty", equalTo(test.getTotalQty())),
                hasProperty("budgetQty", equalTo(test.getBudgetQty())),
                hasProperty("averagePassingGrade", equalTo(test.getAveragePassingGrade())),
                hasProperty("budgetQty", equalTo(test.getBudgetQty()))
        ));
    }

    @Test
    @DisplayName("Test for service : delete 'faculty'")
    void deleteTest() {
        Faculty test = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(test));

        service.delete(FACULTY_ID);
        verify(facultyRepository, times(1)).delete(test);
    }

    @Test
    @DisplayName("Test for service : create 'faculty' with exception")
    void subjectAlreadyExistsTest() {
        FacultyDto testDto = createFacultyDto();
        when(facultyRepository.existsById(FACULTY_ID)).thenReturn(true);

        assertThrows(FacultyAlreadyExistsException.class, () -> service.create(testDto));
    }

    @Test
    @DisplayName("Test for service : find all 'faculties' with exception")
    void subjectListNotFoundTest() {
        assertThrows(FacultyListNotFoundException.class, () -> service.getAll());
    }


    @Test
    @DisplayName("Test for service : find 'faculty' by 'id' with exception")
    void subjectNotFoundTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.empty());

        assertThrows(FacultyNotFoundException.class, () -> service.delete(FACULTY_ID));
    }
}
