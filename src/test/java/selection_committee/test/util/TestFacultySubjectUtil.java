package selection_committee.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.model.Faculty;
import selection_committee.model.FacultySubject;
import selection_committee.model.Subject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestFacultySubjectUtil {

    public static final int FACULTY_SUBJECT_ID = 1;
    private static final Subject SUBJECT = TestSubjectUtil.createSubject();
    private static final Faculty FACULTY = TestFacultyUtil.createFaculty();

    public static FacultySubject createFacultySubject() {
        return FacultySubject.builder()
                .id(FACULTY_SUBJECT_ID)
                .subject(SUBJECT)
                .faculty(FACULTY)
                .build();
    }

    public static FacultySubjectDto createFacultySubjectDto() {
        return FacultySubjectDto.builder()
                .id(FACULTY_SUBJECT_ID)
                .subject(SUBJECT)
                .faculty(FACULTY)
                .build();
    }
}
