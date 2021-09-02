package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.FacultySubjectApi;
import selection_committee.controller.asssembler.FacultySubjectAssembler;
import selection_committee.controller.model.FacultySubjectModel;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.service.FacultySubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacultySubjectController implements FacultySubjectApi {

    private final FacultySubjectService service;
    private final FacultySubjectAssembler assembler;

    @Override
    public List<FacultySubjectModel> getAllByByFacultyId(int facultyId) {
        List<FacultySubjectModel> facultySubjectModels = new ArrayList<>();
        List<FacultySubjectDto> list = service.getAllByByFacultyId(facultyId);
        for (FacultySubjectDto dto : list) {
            facultySubjectModels.add(assembler.toModel(dto));
        }
        return facultySubjectModels;
    }

    @Override
    public FacultySubjectModel create(int facultyId, int subjectId) {
        FacultySubjectDto dto = service.create(facultyId, subjectId);
        return assembler.toModel(dto);
    }

    @Override
    public ResponseEntity<Void> delete(int facultySubjectId) {
        service.delete(facultySubjectId);
        return ResponseEntity.noContent().build();
    }
}
