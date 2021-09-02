package selection_committee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import selection_committee.dto.group.OnCreate;
import selection_committee.model.Faculty;
import selection_committee.model.Subject;

import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultySubjectDto implements Serializable {
    private static final long serialVersionUID = 4596325698741212145L;
    @Null(message = "'subject' should be absent in request", groups = OnCreate.class)
    public Subject subject;
    private int id;
    @Null(message = "'faculty' should be absent in request", groups = OnCreate.class)
    private Faculty faculty;
}
