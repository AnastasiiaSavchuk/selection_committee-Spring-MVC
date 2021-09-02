package selection_committee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selection_committee.model.Grade;
import selection_committee.model.Subject;
import selection_committee.model.User;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findAllByUser(User user);

    boolean existsByUserAndSubject(User user, Subject subject);
}
