package selection_committee.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import selection_committee.dto.FacultySubjectDto;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class FacultySubjectModel extends RepresentationModel<FacultySubjectModel> {

    @JsonUnwrapped
    private FacultySubjectDto facultySubjectDto;
}
