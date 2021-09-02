package selection_committee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import selection_committee.dto.group.OnCreate;
import selection_committee.dto.group.OnUpdate;
import selection_committee.model.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1265894512212365214L;

    private int id;

    @Email(message = "Please check if your 'email' is correct")
    @Null(message = "'email' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'email' shouldn't be empty", groups = OnCreate.class)
    private String email;

    @Null(message = "'password' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'password' shouldn't be empty", groups = OnCreate.class)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "'password' should be minimum eight characters, at least one uppercase letter, one lowercase letter and one number")
    private String password;

    @Null(message = "'confirmPassword' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'confirmPassword' shouldn't be empty", groups = OnCreate.class)
    private String confirmPassword;

    @Builder.Default
    private Role role = Role.USER;

    @NotBlank(message = "'firstName' shouldn't be empty", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "'middleName' shouldn't be empty", groups = OnCreate.class)
    private String middleName;

    @NotBlank(message = "'lastName' shouldn't be empty", groups = OnCreate.class)
    private String lastName;

    @NotBlank(message = "'city' shouldn't be empty", groups = OnCreate.class)
    private String city;

    @NotBlank(message = "'schoolName' shouldn't be empty", groups = OnCreate.class)
    private String schoolName;

    @Builder.Default
    private boolean blockedStatus = false;
}
