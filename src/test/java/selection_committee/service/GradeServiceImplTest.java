package selection_committee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import selection_committee.dto.GradeDto;
import selection_committee.exception.GradeAlreadyExistsException;
import selection_committee.exception.GradeListNotFoundException;
import selection_committee.exception.GradeNotFoundException;
import selection_committee.mapper.GradeMapper;
import selection_committee.model.Grade;
import selection_committee.model.Subject;
import selection_committee.model.User;
import selection_committee.repository.GradeRepository;
import selection_committee.repository.SubjectRepository;
import selection_committee.repository.UserRepository;
import selection_committee.service.impl.GradeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static selection_committee.test.util.TestGradeUtil.*;
import static selection_committee.test.util.TestSubjectUtil.SUBJECT_ID;
import static selection_committee.test.util.TestSubjectUtil.createSubject;
import static selection_committee.test.util.TestUserUtil.USER_ID;
import static selection_committee.test.util.TestUserUtil.createUser;

@ExtendWith(MockitoExtension.class)
class GradeServiceImplTest {

    @Spy
    private final GradeMapper MAPPER = GradeMapper.INSTANCE;
    @InjectMocks
    private GradeServiceImpl service;
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SubjectRepository subjectRepository;

    @Test
    @DisplayName("Test for service : find all 'grades' by 'user'")
    void getAllGradesByUserIdTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        List<Grade> testList = new ArrayList<>();
        testList.add(createGrade());
        when(gradeRepository.findAllByUser(user)).thenReturn(testList);

        List<GradeDto> testDtoList = service.getAllGradesByUserId(USER_ID);
        assertThat(testDtoList.get(0), allOf(
                hasProperty("user", equalTo(testList.get(0).getUser())),
                hasProperty("subject", equalTo(testList.get(0).getSubject())),
                hasProperty("grade", equalTo(testList.get(0).getGrade()))
        ));
    }

    @Test
    @DisplayName("Test for service : find 'grade' by 'id'")
    void getByIdTest() {
        Grade test = createGrade();
        when(gradeRepository.findById(GRADE_ID)).thenReturn(Optional.of(test));

        assertThat(service.getById(GRADE_ID), allOf(
                hasProperty("user", equalTo(test.getUser())),
                hasProperty("subject", equalTo(test.getSubject())),
                hasProperty("grade", equalTo(test.getGrade()))
        ));
    }

    @Test
    @DisplayName("Test for service : create 'grade'")
    void createTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        Subject subject = createSubject();
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.of(subject));

        Grade test = createGrade();
        when(gradeRepository.save(any())).thenReturn(test);

        GradeDto testDto = createGradeDto();
        assertThat(service.create(USER_ID, SUBJECT_ID, testDto), allOf(
                hasProperty("user", equalTo(test.getUser())),
                hasProperty("subject", equalTo(test.getSubject())),
                hasProperty("grade", equalTo(test.getGrade()))
        ));
    }

    @Test
    @DisplayName("Test for service : delete 'grade'")
    void deleteTest() {
        Grade test = createGrade();
        when(gradeRepository.findById(GRADE_ID)).thenReturn(Optional.of(test));

        service.delete(GRADE_ID);
        verify(gradeRepository, times(1)).delete(test);
    }

    @Test
    @DisplayName("Test for service : create 'grade' with exception")
    void gradeAlreadyExistsTest() {
        User user = createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        Subject subject = createSubject();
        when(subjectRepository.findById(SUBJECT_ID)).thenReturn(Optional.of(subject));

        GradeDto testDto = createGradeDto();
        when(gradeRepository.existsByUserAndSubject(testDto.getUser(), testDto.getSubject())).thenReturn(true);

        assertThrows(GradeAlreadyExistsException.class,
                () -> service.create(USER_ID, SUBJECT_ID, testDto));
    }

    @Test
    @DisplayName("Test for service : find all 'grades' with exception")
    void gradeListNotFoundTest() {
        User user = createUser();
        when(userRepository.findById(GRADE_ID)).thenReturn(Optional.of(user));
        assertThrows(GradeListNotFoundException.class, () -> service.getAllGradesByUserId(GRADE_ID));
    }

    @Test
    @DisplayName("Test for service : find 'grade' by 'id' with exception")
    void byIdGradeNotFoundTest() {
        when(gradeRepository.findById(GRADE_ID)).thenReturn(Optional.empty());
        assertThrows(GradeNotFoundException.class, () -> service.getById(GRADE_ID));
    }
}
