package selection_committee.repository.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import selection_committee.model.Certificate;
import selection_committee.repository.CertificateRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CertificateRepositoryImpl implements CertificateRepository {

    private final List<Certificate> list = new ArrayList<>();

    @Override
    public List<Certificate> getAll() {
        return list;
    }

    @Override
    public Certificate getById(int userId) {
        return list.stream()
                .filter(c -> c.getUserId() == userId).findFirst()
                .orElseThrow(() -> new RuntimeException("Certificate is not found!"));
    }

    @Override
    public Certificate create(Certificate certificate) {
        return null;
    }

    @SneakyThrows
    @Override
    public Certificate createFile(int userId, MultipartFile file) {
        return Certificate.builder().userId(userId).certificate(file.getBytes()).build();
    }

    @Override
    public Certificate update(int id, Certificate certificate) {
        return null;
    }

    @Override
    public void delete(int id) {
        list.removeIf(c -> c.getUserId() == id);
    }
}
