package selection_committee.service;

import selection_committee.dto.ApplicationDto;

import java.util.List;

public interface StatementService {

    List<ApplicationDto> create(int facultyId);

    List<ApplicationDto> rollback(int facultyId);
}
