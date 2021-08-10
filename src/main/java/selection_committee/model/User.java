package selection_committee.model;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.enums.Role;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1265894512212365214L;

    private int id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String city;
    private String region;
    private String schoolName;
    private boolean isBlocked;
}
