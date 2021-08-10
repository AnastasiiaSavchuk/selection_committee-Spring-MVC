package selection_committee.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1458756966523100254L;

    private int id;
    private int totalQty;
    private int budgetQty;
    private int averagePassingGrade;
    private String facultyName;
    private List<Subject> subjectList;
}
