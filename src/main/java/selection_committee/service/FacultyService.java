package selection_committee.service;

import selection_committee.dto.FacultyDto;

import java.util.List;

public interface FacultyService {

    List<FacultyDto> getAll();

    FacultyDto getById(int id);

    FacultyDto create(FacultyDto facultyDto);

    FacultyDto update(int id, FacultyDto facultyDto);

    void delete(int id);
}
