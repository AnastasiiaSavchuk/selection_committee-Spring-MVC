package selection_committee.dto;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.User;

import java.io.Serializable;

@Data
@Builder
public class CertificateDto implements Serializable {
    private static final long serialVersionUID = 2547896521459631457L;

    private int userId;
    private byte[] certificate;
}
