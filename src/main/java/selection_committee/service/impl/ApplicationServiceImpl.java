package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.ApplicationDto;
import selection_committee.exception.*;
import selection_committee.mapper.ApplicationMapper;
import selection_committee.model.*;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.*;
import selection_committee.service.ApplicationService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository AR;
    private final FacultyRepository FR;
    private final FacultySubjectRepository FSR;
    private final UserRepository UR;
    private final GradeRepository GR;
    private final ApplicationMapper MAPPER = ApplicationMapper.INSTANCE;

    @Override
    public List<ApplicationDto> getAll() {
        List<Application> list = AR.findAll();
        if (list.isEmpty()) {
            throw new ApplicationListNotFoundException();
        }
        log.info("List of 'application' is found.");
        return MAPPER.mapToListDto(list);
    }

    @Override
    public List<ApplicationDto> getAllByFacultyId(int facultyId) {
        Faculty faculty = FR.findById(facultyId).orElseThrow(FacultyNotFoundException::new);

        List<Application> list = AR.findAllByFaculty(faculty);
        if (list.isEmpty()) {
            throw new ApplicationListNotFoundException();
        }
        log.info("List of 'application' by facultyId {} is found.", facultyId);
        return MAPPER.mapToListDto(list);
    }

    @Override
    public List<ApplicationDto> getAllByUserId(int userId) {
        User user = UR.findById(userId).orElseThrow(UserNotFoundException::new);

        List<Application> list = AR.findAllByUser(user);
        if (list.isEmpty()) {
            throw new ApplicationListNotFoundException();
        }
        log.info("List of 'application' by userId {} is found.", userId);
        return MAPPER.mapToListDto(list);
    }

    @Override
    public ApplicationDto getById(int applicationId) {
        Application application = AR.findById(applicationId).orElseThrow(ApplicationNotFoundException::new);
        log.info("'Application' by id : {} is found.", applicationId);
        return MAPPER.mapToApplicationDto(application);
    }

    @Override
    @Transactional
    public ApplicationDto create(int userId, int facultyId) {
        User user = UR.findById(userId).orElseThrow(UserNotFoundException::new);
        Faculty faculty = FR.findById(facultyId).orElseThrow(FacultyNotFoundException::new);

        if (AR.existsByUserAndFaculty(user, faculty)) {
            throw new ApplicationAlreadyExistsException();
        }
        Application application = Application.builder()
                .user(user)
                .faculty(faculty)
                .sumOfGrades(sumOfGrades(user, faculty))
                .averageGrade(averageGrade(user, faculty))
                .applicationStatus(ApplicationStatus.IN_PROCESSING)
                .build();
        application = AR.save(application);
        log.info("'Application' with id : {} successfully created.", application.getId());
        return MAPPER.mapToApplicationDto(application);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Application application = AR.findById(id).orElseThrow(ApplicationNotFoundException::new);
        AR.delete(application);
        log.info("'Application' with id : {} successfully deleted.", id);
    }

    private int sumOfGrades(User user, Faculty faculty) {
        int sum = 0;
        for (Grade grade : GR.findAllByUser(user)) {
            for (FacultySubject facultySubject : FSR.findAllByFaculty(faculty)) {
                if (grade.getSubject() == facultySubject.getSubject()) {
                    sum += grade.getGrade();
                }
            }
        }
        return sum;
    }

    private int averageGrade(User user, Faculty faculty) {
        return sumOfGrades(user, faculty) / FSR.findAllByFaculty(faculty).size();
    }
}
