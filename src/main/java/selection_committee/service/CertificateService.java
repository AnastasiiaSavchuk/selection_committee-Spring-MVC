package selection_committee.service;

import org.springframework.web.multipart.MultipartFile;
import selection_committee.dto.CertificateDto;
import selection_committee.util.CRUDRepository;

public interface CertificateService extends CRUDRepository<CertificateDto> {

    CertificateDto createFile(int userId, MultipartFile file);
}
