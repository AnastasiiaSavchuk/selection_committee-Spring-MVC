package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import selection_committee.dto.CertificateDto;
import selection_committee.service.CertificateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/certificates")
    public List<CertificateDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/certificateByUser/{userId}")
    public CertificateDto getById(@PathVariable int userId) {
        return service.getById(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createCertificate")
    public CertificateDto createFile(@RequestParam("userId") int userId, @RequestParam("file") MultipartFile file) {
        return service.createFile(userId, file);
    }

    @DeleteMapping(value = "/deleteCertificate/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
