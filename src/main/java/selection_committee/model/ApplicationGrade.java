package selection_committee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationGrade {

    private Application application;
    private Grade grade;
}
