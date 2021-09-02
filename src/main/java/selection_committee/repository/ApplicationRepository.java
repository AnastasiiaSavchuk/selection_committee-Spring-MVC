package selection_committee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selection_committee.model.Application;
import selection_committee.model.Faculty;
import selection_committee.model.User;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findAllByFaculty(Faculty faculty);

    List<Application> findAllByUser(User user);

    boolean existsByUserAndFaculty(User user, Faculty faculty);
}
