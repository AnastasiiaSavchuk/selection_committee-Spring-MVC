package selection_committee.service;

import selection_committee.dto.ApplicationDto;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface ApplicationService extends CRUDRepository<ApplicationDto> {

    List<ApplicationDto> getAllByFacultyId(int facultyId);

    List<ApplicationDto> getAllByUserId(int userId);

    void updateIsSent(int applicationId, ApplicationDto applicationDto);

    boolean isExist(int userId, int facultyId);
}
