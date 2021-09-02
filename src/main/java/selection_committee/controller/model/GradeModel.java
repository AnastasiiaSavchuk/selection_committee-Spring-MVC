package selection_committee.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import selection_committee.dto.GradeDto;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class GradeModel extends RepresentationModel<GradeModel> {

    @JsonUnwrapped
    private GradeDto gradeDto;
}
