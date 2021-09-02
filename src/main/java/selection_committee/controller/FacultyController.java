package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.FacultyApi;
import selection_committee.controller.asssembler.FacultyAssembler;
import selection_committee.controller.model.FacultyModel;
import selection_committee.dto.FacultyDto;
import selection_committee.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacultyController implements FacultyApi {

    private final FacultyService service;
    private final FacultyAssembler assembler;

    @Override
    public List<FacultyModel> getAll() {
        List<FacultyModel> facultyModels = new ArrayList<>();
        List<FacultyDto> list = service.getAll();
        for (FacultyDto dto : list) {
            facultyModels.add(assembler.toModel(dto));
        }
        return facultyModels;
    }

    @Override
    public FacultyModel getById(int facultyId) {
        FacultyDto dto = service.getById(facultyId);
        return assembler.toModel(dto);
    }

    @Override
    public FacultyModel create(FacultyDto facultyDto) {
        FacultyDto dto = service.create(facultyDto);
        return assembler.toModel(dto);
    }

    @Override
    public FacultyModel update(int facultyId, FacultyDto facultyDto) {
        FacultyDto dto = service.update(facultyId, facultyDto);
        return assembler.toModel(dto);
    }

    @Override
    public ResponseEntity<Void> delete(int facultyId) {
        service.delete(facultyId);
        return ResponseEntity.noContent().build();
    }
}
