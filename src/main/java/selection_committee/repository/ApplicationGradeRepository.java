package selection_committee.repository;

import selection_committee.model.ApplicationGrade;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface ApplicationGradeRepository extends CRUDRepository<ApplicationGrade> {

    List<ApplicationGrade> getGradeByApplicationId(int applicationId);
}
