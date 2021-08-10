package selection_committee.service;

import selection_committee.dto.GradeDto;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface GradeService extends CRUDRepository<GradeDto> {

    List<GradeDto> getAllByUserId(int userId);
}
