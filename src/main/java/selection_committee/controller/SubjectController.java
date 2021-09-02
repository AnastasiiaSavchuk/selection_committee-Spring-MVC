package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.SubjectApi;
import selection_committee.controller.asssembler.SubjectAssembler;
import selection_committee.controller.model.SubjectModel;
import selection_committee.dto.SubjectDto;
import selection_committee.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController implements SubjectApi {

    private final SubjectService service;
    private final SubjectAssembler assembler;

    @Override
    public List<SubjectModel> getAll() {
        List<SubjectModel> subjectModels = new ArrayList<>();
        List<SubjectDto> list = service.getAll();
        for (SubjectDto dto : list) {
            subjectModels.add(assembler.toModel(dto));
        }
        return subjectModels;
    }

    @Override
    public SubjectModel getById(int subjectId) {
        SubjectDto dto = service.getById(subjectId);
        return assembler.toModel(dto);
    }

    @Override
    public SubjectModel create(SubjectDto subjectDto) {
        SubjectDto dto = service.create(subjectDto);
        return assembler.toModel(dto);
    }

    @Override
    public SubjectModel update(int subjectId, SubjectDto subjectDto) {
        SubjectDto dto = service.update(subjectId, subjectDto);
        return assembler.toModel(dto);
    }

    @Override
    public ResponseEntity<Void> delete(int subjectId) {
        service.delete(subjectId);
        return ResponseEntity.noContent().build();
    }
}
