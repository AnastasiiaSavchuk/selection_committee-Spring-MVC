package selection_committee.dto;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.Faculty;
import selection_committee.model.Subject;

@Data
@Builder
public class FacultySubjectDto {

    private Faculty faculty;
    public Subject subject;
}
