package selection_committee.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import selection_committee.dto.UserDto;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {

    @JsonUnwrapped
    private UserDto userDto;
}
