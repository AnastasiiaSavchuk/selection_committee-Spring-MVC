package selection_committee.service;

import selection_committee.dto.FacultySubjectDto;

import java.util.List;

public interface FacultySubjectService {

    List<FacultySubjectDto> getAllByByFacultyId(int facultyId);

    FacultySubjectDto getById(int id);

    FacultySubjectDto create(int facultyId, int subjectId);

    void delete(int id);
}
