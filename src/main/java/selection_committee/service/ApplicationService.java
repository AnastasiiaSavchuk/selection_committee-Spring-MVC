package selection_committee.service;

import selection_committee.dto.ApplicationDto;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDto> getAll();

    List<ApplicationDto> getAllByFacultyId(int facultyId);

    List<ApplicationDto> getAllByUserId(int userId);

    ApplicationDto getById(int applicationId);

    ApplicationDto create(int userId, int facultyId);

    void delete(int applicationId);
}
