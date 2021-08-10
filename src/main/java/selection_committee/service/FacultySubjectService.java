package selection_committee.service;


import selection_committee.dto.FacultySubjectDto;
import selection_committee.util.CRUDRepository;

import java.util.List;

public interface FacultySubjectService extends CRUDRepository<FacultySubjectDto> {

    List<FacultySubjectDto> getAllByFacultyId(int facultyId);
}
