package selection_committee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import selection_committee.dto.group.OnCreate;
import selection_committee.model.User;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificateDto implements Serializable {
    private static final long serialVersionUID = 4875965214536985321L;

    @NotBlank(message = "'userId' shouldn't be empty", groups = OnCreate.class)
    private User user;

    @NotBlank(message = "'certificate' shouldn't be empty", groups = OnCreate.class)
    private byte[] certificate;
}
