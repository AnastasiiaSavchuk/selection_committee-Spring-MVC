package selection_committee.service;


import selection_committee.dto.ApplicationGradeDto;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface ApplicationGradeService extends CRUDRepository<ApplicationGradeDto> {

    List<ApplicationGradeDto> getGradeByApplicationId(int applicationId);
}
