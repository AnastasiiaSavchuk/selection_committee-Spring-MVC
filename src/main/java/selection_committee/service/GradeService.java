package selection_committee.service;

import selection_committee.dto.GradeDto;

import java.util.List;

public interface GradeService {

    List<GradeDto> getAllGradesByUserId(int userId);

    GradeDto create(int userId, int subjectId, GradeDto gradeDto);

    void delete(int id);
}
