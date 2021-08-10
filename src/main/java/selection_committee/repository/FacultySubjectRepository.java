package selection_committee.repository;

import selection_committee.model.Faculty;
import selection_committee.model.FacultySubject;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface FacultySubjectRepository extends CRUDRepository<FacultySubject> {

    List<FacultySubject> getAllByFacultyId(int facultyId);
}
