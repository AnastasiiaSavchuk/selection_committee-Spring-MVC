package selection_committee.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SubjectDto implements Serializable {
    private static final long serialVersionUID = 1235478541296523520L;

    private int id;
    private int passingGrade;
    private String subjectName;
}
