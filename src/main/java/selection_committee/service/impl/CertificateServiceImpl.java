package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import selection_committee.dto.CertificateDto;
import selection_committee.model.Certificate;
import selection_committee.model.User;
import selection_committee.repository.CertificateRepository;
import selection_committee.service.CertificateService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;

    @Override
    public List<CertificateDto> getAll() {
        log.info("getCertificate list.");
        List<Certificate> certificates = repository.getAll();
        return mapCertificateListToCertificateDtoList(certificates);
    }

    @Override
    public CertificateDto getById(int userId) {
        log.info("getCertificate by userId : {}.", userId);
        Certificate certificate = repository.getById(userId);
        return mapCertificateToCertificateDto(certificate);
    }

    @Override
    public CertificateDto create(CertificateDto certificateDto) {
        return null;
    }

    @Override
    public CertificateDto createFile(int userId, MultipartFile file) {
        log.info("createCertificate.");
        Certificate certificate = repository.createFile(userId,file);
        return mapCertificateToCertificateDto(certificate);
    }

    @Override
    public CertificateDto update(int id, CertificateDto certificateDto) {
        return null;
    }

    @Override
    public void delete(int id) {
        log.info("deleteCertificate with id : {}.", id);
        repository.delete(id);
    }

    private List<CertificateDto> mapCertificateListToCertificateDtoList(List<Certificate> certificates) {
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        for (Certificate certificate : certificates) {
            certificateDtoList.add(CertificateDto.builder()
                    .userId(certificate.getUserId())
                    .certificate(certificate.getCertificate())
                    .build());
        }
        return certificateDtoList;
    }

    private CertificateDto mapCertificateToCertificateDto(Certificate certificate) {
        return CertificateDto.builder()
                .userId(certificate.getUserId())
                .certificate(certificate.getCertificate())
                .build();
    }

    private Certificate mapCertificateDtoToCertificate(CertificateDto certificateDto) {
        return Certificate.builder()
                .userId(certificateDto.getUserId())
                .certificate(certificateDto.getCertificate())
                .build();
    }
}