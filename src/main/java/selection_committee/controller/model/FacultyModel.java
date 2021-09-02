package selection_committee.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import selection_committee.dto.FacultyDto;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class FacultyModel extends RepresentationModel<FacultyModel> {

    @JsonUnwrapped
    private FacultyDto facultyDto;
}
