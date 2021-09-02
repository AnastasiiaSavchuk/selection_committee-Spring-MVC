package selection_committee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selection_committee.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
