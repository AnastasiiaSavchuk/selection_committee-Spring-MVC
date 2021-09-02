package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.StatementApi;
import selection_committee.controller.asssembler.StatementAssembler;
import selection_committee.controller.model.ApplicationModel;
import selection_committee.dto.ApplicationDto;
import selection_committee.service.StatementService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatementController implements StatementApi {

    private final StatementService SERVICE;
    private final StatementAssembler ASSEMBLER;

    @Override
    public List<ApplicationModel> create(int facultyId) {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        List<ApplicationDto> list = SERVICE.create(facultyId);
        for (ApplicationDto dto : list) {
            applicationModels.add(ASSEMBLER.toModel(dto));
        }
        return applicationModels;
    }

    @Override
    public List<ApplicationModel> rollback(int facultyId) {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        List<ApplicationDto> list = SERVICE.rollback(facultyId);
        for (ApplicationDto dto : list) {
            applicationModels.add(ASSEMBLER.toModel(dto));
        }
        return applicationModels;
    }
}
