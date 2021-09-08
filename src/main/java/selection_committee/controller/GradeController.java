package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.GradeApi;
import selection_committee.controller.asssembler.GradeAssembler;
import selection_committee.controller.model.GradeModel;
import selection_committee.dto.GradeDto;
import selection_committee.service.GradeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GradeController implements GradeApi {

    private final GradeService SERVICE;
    private final GradeAssembler ASSEMBLER;

    @Override
    public List<GradeModel> findAllGradesByUserId(int userId) {
        List<GradeModel> gradeModels = new ArrayList<>();
        List<GradeDto> list = SERVICE.getAllGradesByUserId(userId);
        for (GradeDto dto : list) {
            gradeModels.add(ASSEMBLER.toModel(dto));
        }
        return gradeModels;
    }

    @Override
    public GradeModel create(int userId, int subjectId, GradeDto gradeDto) {
        GradeDto dto = SERVICE.create(userId, subjectId, gradeDto);
        return ASSEMBLER.toModel(dto);
    }

    @Override
    public ResponseEntity<Void> delete(int gradeId) {
        SERVICE.delete(gradeId);
        return ResponseEntity.noContent().build();
    }
}
