package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.ApplicationGradeDto;
import selection_committee.model.Application;
import selection_committee.model.ApplicationGrade;
import selection_committee.repository.ApplicationGradeRepository;
import selection_committee.service.ApplicationGradeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationGradeServiceImpl implements ApplicationGradeService {

    private final ApplicationGradeRepository repository;

    @Override
    public List<ApplicationGradeDto> getAll() {
        log.info("getApplicationGrade list.");
        List<ApplicationGrade> applications = repository.getAll();
        return mapApplicationGradeListToApplicationGradeDtoList(applications);
    }

    @Override
    public List<ApplicationGradeDto> getGradeByApplicationId(int applicationId) {
        log.info("getApplicationGrade list by applicationId.");
        List<ApplicationGrade> applications = repository.getGradeByApplicationId(applicationId);
        return mapApplicationGradeListToApplicationGradeDtoList(applications);
    }

    @Override
    public ApplicationGradeDto getById(int id) {
        return null;
    }

    @Override
    public ApplicationGradeDto create(ApplicationGradeDto applicationGradeDto) {
        log.info("createApplicationGrade");
        ApplicationGrade applicationGrade = mapApplicationGradeDtoToApplicationGrade(applicationGradeDto);
        applicationGrade = repository.create(applicationGrade);
        return mapApplicationGradeToApplicationGradeDto(applicationGrade);
    }

    @Override
    public ApplicationGradeDto update(int id, ApplicationGradeDto applicationGradeDto) {
        return null;
    }

    @Override
    public void delete(int gradeId) {
        log.info("deleteApplicationGrade with gradeId : {}.", gradeId);
        repository.delete(gradeId);
    }

    private ApplicationGradeDto mapApplicationGradeToApplicationGradeDto(ApplicationGrade applicationGrade) {
        return ApplicationGradeDto.builder()
                .application(applicationGrade.getApplication())
                .grade(applicationGrade.getGrade())
                .build();
    }

    private List<ApplicationGradeDto> mapApplicationGradeListToApplicationGradeDtoList(List<ApplicationGrade> applicationGrades) {
        List<ApplicationGradeDto> applicationGradeDtoList = new ArrayList<>();
        for (ApplicationGrade applicationGrade : applicationGrades) {
            applicationGradeDtoList.add(ApplicationGradeDto.builder()
                    .application(applicationGrade.getApplication())
                    .grade(applicationGrade.getGrade())
                    .build());
        }
        return applicationGradeDtoList;
    }

    private ApplicationGrade mapApplicationGradeDtoToApplicationGrade(ApplicationGradeDto applicationGradeDto) {
        return ApplicationGrade.builder()
                .application(applicationGradeDto.getApplication())
                .grade(applicationGradeDto.getGrade())
                .build();
    }

}
