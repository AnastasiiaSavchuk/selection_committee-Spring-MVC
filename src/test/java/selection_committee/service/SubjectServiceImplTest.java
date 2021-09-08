package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.SubjectDto;
import selection_committee.exception.SubjectAlreadyExistsException;
import selection_committee.exception.SubjectListNotFoundException;
import selection_committee.exception.SubjectNotFoundException;
import selection_committee.mapper.SubjectMapper;
import selection_committee.model.Subject;
import selection_committee.repository.SubjectRepository;
import selection_committee.service.impl.SubjectServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static selection_committee.test.util.TestSubjectUtil.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Spy
    private final SubjectMapper MAPPER = SubjectMapper.INSTANCE;
    @InjectMocks
    private SubjectServiceImpl service;
    @Mock
    private SubjectRepository subjectRepository;

    @Test
    @DisplayName("Test for service : find all 'subjects'")
    void getAllTest() {
        List<Subject> testList = new ArrayList<>();
        testList.add(createSubject());
        when(subjectRepository.findAll()).thenReturn(testList);

        List<SubjectDto> testDtoList = service.getAll();
        assertThat(testDtoList.get(0), allOf(
                hasProperty("passingGrade", equalTo(testList.get(0).getPassingGrade())),
                hasProperty("subjectName", equalTo(testList.get(0).getSubjectName()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'subject' by 'id'")
    void getByIdTest() {
        Subject test = createSubject();
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.of(test));

        assertThat(service.getById(SUBJECT_ID), allOf(
                hasProperty("passingGrade", equalTo(test.getPassingGrade())),
                hasProperty("subjectName", equalTo(test.getSubjectName()))
        ));
    }

    @Test
    @DisplayName("Test for service : create 'subject'")
    public void createTest() {
        Subject test = createSubject();
        when(subjectRepository.save(any())).thenReturn(test);

        SubjectDto testDto = createSubjectDto();
        assertThat(service.create(testDto), allOf(
                hasProperty("passingGrade", equalTo(test.getPassingGrade())),
                hasProperty("subjectName", equalTo(test.getSubjectName()))
        ));
    }

    @Test
    @DisplayName("Test for service : update 'subject'")
    public void updateTest() {
        Subject test = createSubject();
        when(subjectRepository.save(any())).thenReturn(test);

        SubjectDto testDto = createSubjectDto();
        assertThat(service.update(SUBJECT_ID, testDto), allOf(
                hasProperty("passingGrade", equalTo(test.getPassingGrade())),
                hasProperty("subjectName", equalTo(test.getSubjectName()))
        ));
    }

    @Test
    @DisplayName("Test for service : delete 'subject'")
    void deleteTest() {
        Subject test = createSubject();
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.of(test));

        service.delete(SUBJECT_ID);
        verify(subjectRepository, times(1)).delete(test);
    }

    @Test
    @DisplayName("Test for service : create 'subject' with exception")
    public void createSubjectAlreadyExistsTest() {
        SubjectDto testDto = createSubjectDto();
        when(subjectRepository.existsById(SUBJECT_ID)).thenReturn(true);

        assertThrows(SubjectAlreadyExistsException.class, () -> service.create(testDto));
    }

    @Test
    @DisplayName("Test for service : find all 'subjects' with exception")
    void getAllSubjectListNotFoundTest() {
        assertThrows(SubjectListNotFoundException.class, () -> service.getAll());
    }

    @Test
    @DisplayName("Test for service : find 'subject' by 'id' with exception")
    void subjectNotFoundTest() {
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.empty());

        assertThrows(SubjectNotFoundException.class, () -> service.delete(SUBJECT_ID));
    }
}
