package selection_committee.repository;

import selection_committee.model.Application;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface ApplicationRepository extends CRUDRepository<Application> {

    List<Application> getAllByFacultyId(int facultyId);

    List<Application> getAllByUserId(int userId);

    void updateIsSent(int applicationId, Application application);

    boolean isExist(int userId, int facultyId);
}
