package selection_committee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import selection_committee.dto.group.OnCreate;
import selection_committee.dto.group.OnUpdate;
import selection_committee.model.Faculty;
import selection_committee.model.User;
import selection_committee.model.enums.ApplicationStatus;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationDto implements Serializable {
    private static final long serialVersionUID = 2365841213412352526L;

    private int id;

    @NotBlank(message = "'user' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private User user;

    @NotBlank(message = "'faculty' shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private Faculty faculty;

    @Builder.Default
    private int sumOfGrades = 0;

    @Builder.Default
    private int averageGrade = 0;

    @Builder.Default
    private ApplicationStatus applicationStatus = ApplicationStatus.IN_PROCESSING;
}
