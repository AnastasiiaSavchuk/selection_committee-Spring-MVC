package selection_committee.model;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.enums.ApplicationStatus;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Application implements Serializable {
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
