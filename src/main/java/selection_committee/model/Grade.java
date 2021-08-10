package selection_committee.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Grade implements Serializable {
    private static final long serialVersionUID = 3021456952147512580L;

    private int id;
    private User user;
    private Subject subject;
    private int grade;
}
