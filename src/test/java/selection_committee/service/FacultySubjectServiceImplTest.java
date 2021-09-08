package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.exception.FacultySubjectAlreadyExistsException;
import selection_committee.exception.FacultySubjectListNotFoundException;
import selection_committee.exception.FacultySubjectNotFoundException;
import selection_committee.mapper.FacultySubjectMapper;
import selection_committee.model.Faculty;
import selection_committee.model.FacultySubject;
import selection_committee.model.Subject;
import selection_committee.repository.FacultyRepository;
import selection_committee.repository.FacultySubjectRepository;
import selection_committee.repository.SubjectRepository;
import selection_committee.service.impl.FacultySubjectServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static selection_committee.test.util.TestFacultySubjectUtil.*;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestFacultyUtil.createFaculty;
import static selection_committee.test.util.TestSubjectUtil.SUBJECT_ID;
import static selection_committee.test.util.TestSubjectUtil.createSubject;

@ExtendWith(MockitoExtension.class)
class FacultySubjectServiceImplTest {

    @Spy
    private final FacultySubjectMapper MAPPER = FacultySubjectMapper.INSTANCE;
    @InjectMocks
    private FacultySubjectServiceImpl service;
    @Mock
    private FacultySubjectRepository facultySubjectRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private SubjectRepository subjectRepository;

    @Test
    @DisplayName("Test for service : find all 'subjects' by 'faculty'")
    void getAllByByFacultyIdTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        List<FacultySubject> testList = new ArrayList<>();
        testList.add(createFacultySubject());
        when(facultySubjectRepository.findAllByFaculty(faculty)).thenReturn(testList);

        List<FacultySubjectDto> testDtoList = service.getAllByByFacultyId(FACULTY_ID);
        assertThat(testDtoList.get(0), allOf(
                hasProperty("subject", equalTo(testList.get(0).getSubject())),
                hasProperty("faculty", equalTo(testList.get(0).getFaculty()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'faculty_subject' by 'id'")
    void getByIdTest() {
        FacultySubject test = createFacultySubject();
        when(facultySubjectRepository.findById(FACULTY_SUBJECT_ID)).thenReturn(Optional.of(test));

        assertThat(service.getById(FACULTY_SUBJECT_ID), allOf(
                hasProperty("subject", equalTo(test.getSubject())),
                hasProperty("faculty", equalTo(test.getFaculty()))
        ));
    }

    @Test
    @DisplayName("Test for service : add 'subject' to the 'faculty'")
    void createTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Subject subject = createSubject();
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.of(subject));

        FacultySubject test = createFacultySubject();
        when(facultySubjectRepository.save(any())).thenReturn(test);

        List<FacultySubject> testList = new ArrayList<>();
        testList.add(createFacultySubject());
        when(facultySubjectRepository.findAllByFaculty(faculty)).thenReturn(testList);

        assertThat(service.create(FACULTY_ID, SUBJECT_ID), allOf(
                hasProperty("subject", equalTo(test.getSubject())),
                hasProperty("faculty", equalTo(test.getFaculty()))
        ));
    }

    @Test
    @DisplayName("Test for service : delete 'subject' from 'faculty'")
    void deleteTest() {
        FacultySubject test = createFacultySubject();
        when(facultySubjectRepository.findById(FACULTY_SUBJECT_ID)).thenReturn(Optional.of(test));

        service.delete(FACULTY_SUBJECT_ID);
        verify(facultySubjectRepository, times(1)).delete(test);
    }

    @Test
    @DisplayName("Test for service : add 'subject' to 'faculty' with exception")
    void facultySubjectAlreadyExistsTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));

        Subject subject = createSubject();
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.of(subject));

        FacultySubjectDto testDto = createFacultySubjectDto();
        when(facultySubjectRepository.existsByFacultyAndSubject(testDto.getFaculty(), testDto.getSubject())).thenReturn(true);

        assertThrows(FacultySubjectAlreadyExistsException.class, () -> service.create(FACULTY_ID, SUBJECT_ID));
    }

    @Test
    @DisplayName("Test for service : find all 'subjects' by 'faculty' with exception")
    void facultySubjectListNotFoundTest() {
        Faculty faculty = createFaculty();
        when(facultyRepository.findById(FACULTY_ID)).thenReturn(Optional.of(faculty));
        assertThrows(FacultySubjectListNotFoundException.class, () -> service.getAllByByFacultyId(FACULTY_ID));
    }

    @Test
    @DisplayName("Test for service : find 'faculty_subject' by 'id' with exception")
    void byIdFacultySubjectNotFoundTest() {
        when(facultySubjectRepository.findById(FACULTY_SUBJECT_ID)).thenReturn(Optional.empty());
        assertThrows(FacultySubjectNotFoundException.class, () -> service.getById(FACULTY_SUBJECT_ID));
    }
}
