package selection_committee.service;

import selection_committee.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAll();

    SubjectDto getById(int id);

    SubjectDto create(SubjectDto subjectDto);

    SubjectDto update(int id, SubjectDto subjectDto);

    void delete(int id);
}
