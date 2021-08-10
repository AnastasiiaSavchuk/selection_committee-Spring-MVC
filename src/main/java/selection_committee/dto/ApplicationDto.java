package selection_committee.dto;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.Faculty;
import selection_committee.model.Grade;
import selection_committee.model.User;
import selection_committee.model.enums.ApplicationStatus;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ApplicationDto implements Serializable {
    private static final long serialVersionUID = 2365841213412352526L;

    private int id;
    private User user;
    private Faculty faculty;
    private int sumOfGrades;
    private int averageGrade;
    private ApplicationStatus applicationStatus;
    private List<Grade> grades;
    private boolean isSent;
}
