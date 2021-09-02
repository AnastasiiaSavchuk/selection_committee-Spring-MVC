package selection_committee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selection_committee.model.Faculty;
import selection_committee.model.FacultySubject;
import selection_committee.model.Subject;

import java.util.List;

@Repository
public interface FacultySubjectRepository extends JpaRepository<FacultySubject, Integer> {

    List<FacultySubject> findAllByFaculty(Faculty faculty);

    boolean existsByFacultyAndSubject(Faculty faculty, Subject subject);
}
