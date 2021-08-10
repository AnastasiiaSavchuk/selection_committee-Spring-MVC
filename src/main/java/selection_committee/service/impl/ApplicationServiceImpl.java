package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.ApplicationDto;
import selection_committee.model.Application;
import selection_committee.repository.ApplicationRepository;
import selection_committee.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    @Override
    public List<ApplicationDto> getAll() {
        log.info("getApplication list.");
        List<Application> applications = repository.getAll();
        return mapApplicationListToApplicationDtoList(applications);
    }

    @Override
    public List<ApplicationDto> getAllByFacultyId(int facultyId) {
        log.info("getApplication list by facultyId : {}.", facultyId);
        List<Application> application = repository.getAllByFacultyId(facultyId);
        return mapApplicationListToApplicationDtoList(application);
    }

    @Override
    public List<ApplicationDto> getAllByUserId(int userId) {
        log.info("getApplication list by userId : {}.", userId);
        List<Application> application = repository.getAllByUserId(userId);
        return mapApplicationListToApplicationDtoList(application);
    }

    @Override
    public ApplicationDto getById(int id) {
        log.info("getApplication by id : {}.", id);
        Application application = repository.getById(id);
        return mapApplicationToApplicationDto(application);
    }


    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        log.info("createApplication");
        Application application = mapApplicationDtoToApplication(applicationDto);
        application = repository.create(application);
        return mapApplicationToApplicationDto(application);
    }

    @Override
    public ApplicationDto update(int id, ApplicationDto applicationDto) {
        log.info("updateApplication with id : {}", id);
        Application application = mapApplicationDtoToApplication(applicationDto);
        application = repository.update(id, application);
        return mapApplicationToApplicationDto(application);
    }

    @Override
    public void updateIsSent(int applicationId, ApplicationDto applicationDto) {
        log.info("updateApplicationIsSent with applicationId : {}", applicationDto);
        Application application = mapApplicationDtoToApplication(applicationDto);
        repository.updateIsSent(applicationId, application);
    }

    @Override
    public boolean isExist(int userId, int facultyId) {
        log.info("isExist application by userId : {}, and facultyId : {}.", userId, facultyId);
        return repository.isExist(userId, facultyId);
    }

    @Override
    public void delete(int id) {
        log.info("deleteApplication with gradeId : {}.", id);
        repository.delete(id);
    }

    private ApplicationDto mapApplicationToApplicationDto(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .user(application.getUser())
                .faculty(application.getFaculty())
                .sumOfGrades(application.getSumOfGrades())
                .averageGrade(application.getAverageGrade())
                .grades(application.getGrades())
                .build();
    }

    private List<ApplicationDto> mapApplicationListToApplicationDtoList(List<Application> applications) {
        List<ApplicationDto> applicationDtoList = new ArrayList<>();
        for (Application application : applications) {
            applicationDtoList.add(ApplicationDto.builder()
                    .id(application.getId())
                    .user(application.getUser())
                    .faculty(application.getFaculty())
                    .sumOfGrades(application.getSumOfGrades())
                    .averageGrade(application.getAverageGrade())
                    .grades(application.getGrades())
                    .isSent(application.isSent())
                    .build());
        }
        return applicationDtoList;
    }

    private Application mapApplicationDtoToApplication(ApplicationDto applicationDto) {
        return Application.builder()
                .id(applicationDto.getId())
                .user(applicationDto.getUser())
                .faculty(applicationDto.getFaculty())
                .sumOfGrades(applicationDto.getSumOfGrades())
                .averageGrade(applicationDto.getAverageGrade())
                .grades(applicationDto.getGrades())
                .isSent(applicationDto.isSent())
                .build();
    }
}
