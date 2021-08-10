package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.model.FacultySubject;
import selection_committee.repository.FacultySubjectRepository;
import selection_committee.service.FacultySubjectService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultySubjectServiceImpl implements FacultySubjectService {

    private final FacultySubjectRepository repository;

    @Override
    public List<FacultySubjectDto> getAll() {
        log.info("getFacultySubject list.");
        List<FacultySubject> facultySubjects = repository.getAll();
        return mapFacultySubjectListToFacultySubjectDtoList(facultySubjects);
    }

    @Override
    public List<FacultySubjectDto> getAllByFacultyId(int facultyId) {
        log.info("getFacultySubject list by facultyId.");
        List<FacultySubject> facultySubjects = repository.getAllByFacultyId(facultyId);
        return mapFacultySubjectListToFacultySubjectDtoList(facultySubjects);
    }

    @Override
    public FacultySubjectDto getById(int id) {
        return null;
    }

    @Override
    public FacultySubjectDto create(FacultySubjectDto facultySubjectDto) {
        log.info("createFacultySubject.");
        FacultySubject facultySubject = mapFacultySubjectDtoToFacultySubject(facultySubjectDto);
        facultySubject = repository.create(facultySubject);
        return mapFacultySubjectToFacultySubjectDto(facultySubject);
    }

    @Override
    public FacultySubjectDto update(int subjectId, FacultySubjectDto facultySubjectDto) {
        log.info("updateFacultySubject with subjectId : {}", subjectId);
        FacultySubject facultySubject = mapFacultySubjectDtoToFacultySubject(facultySubjectDto);
        facultySubject = repository.update(subjectId, facultySubject);
        return mapFacultySubjectToFacultySubjectDto(facultySubject);
    }

    @Override
    public void delete(int subjectId) {
        log.info("deleteFacultySubject with subjectId : {}.", subjectId);
        repository.delete(subjectId);
    }

    private FacultySubjectDto mapFacultySubjectToFacultySubjectDto(FacultySubject facultySubject) {
        return FacultySubjectDto.builder()
                .faculty(facultySubject.getFaculty())
                .subject(facultySubject.getSubject())
                .build();
    }

    private List<FacultySubjectDto> mapFacultySubjectListToFacultySubjectDtoList(List<FacultySubject> facultySubjects) {
        List<FacultySubjectDto> facultySubjectDtoList = new ArrayList<>();
        for (FacultySubject facultySubject : facultySubjects) {
            facultySubjectDtoList.add(FacultySubjectDto.builder()
                    .faculty(facultySubject.getFaculty())
                    .subject(facultySubject.getSubject())
                    .build());
        }
        return facultySubjectDtoList;
    }

    private FacultySubject mapFacultySubjectDtoToFacultySubject(FacultySubjectDto facultySubjectDto) {
        return FacultySubject.builder()
                .faculty(facultySubjectDto.getFaculty())
                .subject(facultySubjectDto.getSubject())
                .build();
    }
}
