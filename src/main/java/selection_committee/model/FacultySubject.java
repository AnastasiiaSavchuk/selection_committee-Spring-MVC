package selection_committee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacultySubject {

    private Faculty faculty;
    public Subject subject;
}
