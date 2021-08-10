package selection_committee.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Certificate implements Serializable {
    private static final long serialVersionUID = 2547896521459631457L;

    private int userId;
    private byte[] certificate;
}
