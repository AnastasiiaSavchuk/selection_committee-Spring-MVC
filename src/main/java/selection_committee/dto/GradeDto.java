package selection_committee.dto;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.Subject;
import selection_committee.model.User;

import java.io.Serializable;

@Data
@Builder
public class GradeDto implements Serializable {
    private static final long serialVersionUID = 3021456952147512580L;

    private int id;
    private User user;
    private Subject subject;
    private int grade;
}
