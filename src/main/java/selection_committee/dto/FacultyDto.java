package selection_committee.dto;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.Subject;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class FacultyDto implements Serializable {
    private static final long serialVersionUID = 1458756966523100254L;

    private int id;
    private int budgetQty;
    private int totalQty;
    private int averagePassingGrade;
    private String facultyName;
    private List<Subject> subjectList;
}
