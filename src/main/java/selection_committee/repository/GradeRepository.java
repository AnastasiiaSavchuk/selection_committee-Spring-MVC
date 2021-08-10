package selection_committee.repository;

import selection_committee.model.Grade;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface GradeRepository extends CRUDRepository<Grade> {

    List<Grade> getAllByUserId(int userId);
}
