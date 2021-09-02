package selection_committee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selection_committee.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
