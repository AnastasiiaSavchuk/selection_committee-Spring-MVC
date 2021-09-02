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
public class FacultyDto implements Serializable {
    private static final long serialVersionUID = 1458756966523100254L;

    private int id;

    @Min(value = 0, message = "'totalQty' shouldn't be less then 0")
    @Max(value = 40, message = "'totalQty' shouldn't be more then 40")
    @NotNull(message = "'totalQty' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private int totalQty;

    @Min(value = 0, message = "'budgetQty' shouldn't be less then 0")
    @Max(value = 30, message = "'budgetQty' shouldn't be more then 30")
    @NotNull(message = "'budgetQty' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private int budgetQty;

    @Builder.Default
    @Min(value = 0, message = "'averagePassingGrade' shouldn't be less then 0")
    @Max(value = 200, message = "'averagePassingGrade' shouldn't be more then 200")
    private int averagePassingGrade = 0;

    @NotBlank(message = "'facultyName' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String facultyName;
}
