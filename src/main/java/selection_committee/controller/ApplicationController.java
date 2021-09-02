package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.ApplicationApi;
import selection_committee.controller.asssembler.ApplicationAssembler;
import selection_committee.controller.model.ApplicationModel;
import selection_committee.dto.ApplicationDto;
import selection_committee.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController implements ApplicationApi {

    private final ApplicationService service;
    private final ApplicationAssembler assembler;

    @Override
    public List<ApplicationModel> getAll() {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        List<ApplicationDto> list = service.getAll();
        for (ApplicationDto dto : list) {
            applicationModels.add(assembler.toModel(dto));
        }
        return applicationModels;
    }

    @Override
    public List<ApplicationModel> getAllByFacultyId(int facultyId) {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        List<ApplicationDto> list = service.getAllByFacultyId(facultyId);
        for (ApplicationDto dto : list) {
            applicationModels.add(assembler.toModel(dto));
        }
        return applicationModels;
    }

    @Override
    public List<ApplicationModel> getAllByUserId(int userId) {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        List<ApplicationDto> list = service.getAllByUserId(userId);
        for (ApplicationDto dto : list) {
            applicationModels.add(assembler.toModel(dto));
        }
        return applicationModels;
    }

    @Override
    public ApplicationModel create(int userId, int facultyId) {
        ApplicationDto dto = service.create(userId, facultyId);
        return assembler.toModel(dto);
    }

    @Override
    public ResponseEntity<Void> delete(int applicationId) {
        service.delete(applicationId);
        return ResponseEntity.noContent().build();
    }
}
