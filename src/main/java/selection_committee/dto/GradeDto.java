package selection_committee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import selection_committee.dto.group.OnCreate;
import selection_committee.model.Subject;
import selection_committee.model.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GradeDto implements Serializable {
    private static final long serialVersionUID = 3021456952147512580L;

    private int id;

    @Null(message = "'user' should be absent in request", groups = OnCreate.class)
    private User user;

    @Null(message = "'subject' should be absent in request", groups = OnCreate.class)
    private Subject subject;

    @Min(value = 0, message = "'grade' shouldn't be less then 0")
    @Max(value = 200, message = "'grade' shouldn't be more then 200")
    @NotNull(message = "'grade' shouldn't be empty", groups = OnCreate.class)
    private int grade;
}
