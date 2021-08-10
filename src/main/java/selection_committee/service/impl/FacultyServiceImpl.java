package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.FacultyDto;
import selection_committee.model.Faculty;
import selection_committee.repository.FacultyRepository;
import selection_committee.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository repository;

    @Override
    public List<FacultyDto> getAll() {
        log.info("getFaculty list.");
        List<Faculty> faculties = repository.getAll();
        return mapFacultyListToFacultyDtoList(faculties);
    }

    @Override
    public FacultyDto getById(int id) {
        log.info("getFaculty by id : {}.", id);
        Faculty faculty = repository.getById(id);
        return mapFacultyToFacultyDto(faculty);
    }

    @Override
    public FacultyDto create(FacultyDto facultyDto) {
        log.info("createFaculty.");
        Faculty faculty = mapFacultyDtoToFaculty(facultyDto);
        faculty = repository.create(faculty);
        return mapFacultyToFacultyDto(faculty);
    }

    @Override
    public FacultyDto update(int id, FacultyDto facultyDto) {
        log.info("updateFaculty with id : {}", id);
        Faculty faculty = mapFacultyDtoToFaculty(facultyDto);
        faculty = repository.update(id, faculty);
        return mapFacultyToFacultyDto(faculty);
    }

    @Override
    public void delete(int id) {
        log.info("deleteFaculty with id : {}.", id);
        repository.delete(id);
    }

    private FacultyDto mapFacultyToFacultyDto(Faculty faculty) {
        return FacultyDto.builder()
                .id(faculty.getId())
                .totalQty(faculty.getTotalQty())
                .budgetQty(faculty.getBudgetQty())
                .averagePassingGrade(faculty.getAveragePassingGrade())
                .facultyName(faculty.getFacultyName())
                .subjectList(faculty.getSubjectList())
                .build();
    }

    private List<FacultyDto> mapFacultyListToFacultyDtoList(List<Faculty> faculties) {
        List<FacultyDto> facultyDtoList = new ArrayList<>();
        for (Faculty faculty : faculties) {
            facultyDtoList.add(FacultyDto.builder()
                    .id(faculty.getId())
                    .totalQty(faculty.getTotalQty())
                    .budgetQty(faculty.getBudgetQty())
                    .averagePassingGrade(faculty.getAveragePassingGrade())
                    .facultyName(faculty.getFacultyName())
                    .subjectList(faculty.getSubjectList())
                    .build());
        }
        return facultyDtoList;
    }

    private Faculty mapFacultyDtoToFaculty(FacultyDto facultyDto) {
        return Faculty.builder()
                .id(facultyDto.getId())
                .totalQty(facultyDto.getTotalQty())
                .budgetQty(facultyDto.getBudgetQty())
                .averagePassingGrade(facultyDto.getAveragePassingGrade())
                .facultyName(facultyDto.getFacultyName())
                .subjectList(facultyDto.getSubjectList())
                .build();
    }
}
