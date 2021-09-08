package selection_committee.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import selection_committee.dto.FacultyDto;
import selection_committee.model.Faculty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestFacultyUtil {

    public static final int FACULTY_ID = 1;
    private static final int TOTAL_QTY = 4;
    private static final int BUDGET_QTY = 2;
    private static final int AVERAGE_GRADE = 140;
    private static final String FACULTY_NAME = "Test_FacultyName";

    public static Faculty createFaculty() {
        return Faculty.builder()
                .id(FACULTY_ID)
                .totalQty(TOTAL_QTY)
                .budgetQty(BUDGET_QTY)
                .averagePassingGrade(AVERAGE_GRADE)
                .facultyName(FACULTY_NAME)
                .build();
    }

    public static FacultyDto createFacultyDto() {
        return FacultyDto.builder()
                .id(FACULTY_ID)
                .totalQty(TOTAL_QTY)
                .budgetQty(BUDGET_QTY)
                .averagePassingGrade(AVERAGE_GRADE)
                .facultyName(FACULTY_NAME)
                .build();
    }
}
