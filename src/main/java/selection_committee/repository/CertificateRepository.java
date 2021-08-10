package selection_committee.repository;

import org.springframework.web.multipart.MultipartFile;
import selection_committee.model.Certificate;
import selection_committee.model.User;
import selection_committee.util.CRUDRepository;

public interface CertificateRepository extends CRUDRepository<Certificate> {

    Certificate createFile(int userId, MultipartFile file);
}
