package selection_committee.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import selection_committee.dto.SubjectDto;
import selection_committee.model.Subject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestSubjectUtil {

    public static final int SUBJECT_ID = 1;
    private static final int PASSING_GRADE = 159;
    private static final String SUBJECT_NAME = "Test_SubjectName";

    public static Subject createSubject() {
        return Subject.builder()
                .id(SUBJECT_ID)
                .passingGrade(PASSING_GRADE)
                .subjectName(SUBJECT_NAME)
                .build();
    }

    public static SubjectDto createSubjectDto() {
        return SubjectDto.builder()
                .id(SUBJECT_ID)
                .passingGrade(PASSING_GRADE)
                .subjectName(SUBJECT_NAME)
                .build();
    }
}
