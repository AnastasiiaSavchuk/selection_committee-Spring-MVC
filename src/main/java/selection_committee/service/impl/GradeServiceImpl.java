package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.GradeDto;
import selection_committee.model.Grade;
import selection_committee.repository.GradeRepository;
import selection_committee.service.GradeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository repository;

    @Override
    public List<GradeDto> getAll() {
        log.info("getGrade list.");
        List<Grade> grades = repository.getAll();
        return mapGradeListToGradeDtoList(grades);
    }

    @Override
    public List<GradeDto> getAllByUserId(int userId) {
        log.info("getGrade list by userId : {}.", userId);
        List<Grade> grades = repository.getAllByUserId(userId);
        return mapGradeListToGradeDtoList(grades);
    }

    @Override
    public GradeDto getById(int id) {
        log.info("getGrade by id : {}.", id);
        Grade g = repository.getById(id);
        return mapGradeToGradeDto(g);
    }

    @Override
    public GradeDto create(GradeDto gradeDto) {
        log.info("createGrade.");
        Grade grade = mapGradeDtoToGrade(gradeDto);
        grade = repository.create(grade);
        return mapGradeToGradeDto(grade);
    }

    @Override
    public GradeDto update(int id, GradeDto gradeDto) {
        log.info("updateGrade with id : {}", id);
        Grade grade = mapGradeDtoToGrade(gradeDto);
        grade = repository.update(id, grade);
        return mapGradeToGradeDto(grade);
    }

    @Override
    public void delete(int id) {
        log.info("deleteGrade with id : {}.", id);
        repository.delete(id);
    }

    private GradeDto mapGradeToGradeDto(Grade grade) {
        return GradeDto.builder()
                .id(grade.getId())
                .user(grade.getUser())
                .subject(grade.getSubject())
                .grade(grade.getGrade()).build();
    }

    private List<GradeDto> mapGradeListToGradeDtoList(List<Grade> grades) {
        List<GradeDto> gradeDtoList = new ArrayList<>();
        for (Grade grade : grades) {
            gradeDtoList.add(GradeDto.builder()
                    .id(grade.getId())
                    .user(grade.getUser())
                    .subject(grade.getSubject())
                    .grade(grade.getGrade()).build());
        }
        return gradeDtoList;
    }

    private Grade mapGradeDtoToGrade(GradeDto gradeDto) {
        return Grade.builder()
                .id(gradeDto.getId())
                .user(gradeDto.getUser())
                .subject(gradeDto.getSubject())
                .grade(gradeDto.getGrade()).build();
    }
}
