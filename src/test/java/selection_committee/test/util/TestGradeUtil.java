package selection_committee.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import selection_committee.dto.GradeDto;
import selection_committee.model.Grade;
import selection_committee.model.Subject;
import selection_committee.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestGradeUtil {

    public static final int GRADE_ID = 1;
    private static final User USER = TestUserUtil.createUser();
    private static final Subject SUBJECT = TestSubjectUtil.createSubject();
    private static final int GRADE = 189;

    public static Grade createGrade() {
        return Grade.builder()
                .id(GRADE_ID)
                .user(USER)
                .subject(SUBJECT)
                .grade(GRADE)
                .build();
    }

    public static GradeDto createGradeDto() {
        return GradeDto.builder()
                .id(GRADE_ID)
                .user(USER)
                .subject(SUBJECT)
                .grade(GRADE)
                .build();
    }
}
