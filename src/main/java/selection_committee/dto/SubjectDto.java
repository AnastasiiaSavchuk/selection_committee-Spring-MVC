package selection_committee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import selection_committee.dto.group.OnCreate;
import selection_committee.dto.group.OnUpdate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto implements Serializable {
    private static final long serialVersionUID = 1235478541296523520L;

    private int id;

    @Min(value = 0, message = "'passingGrade' shouldn't be less then 0")
    @Max(value = 200, message = "'passingGrade' shouldn't be more then 200")
    @NotNull(message = "'passingGrade' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private int passingGrade;

    @NotBlank(message = "'subjectName' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String subjectName;
}
