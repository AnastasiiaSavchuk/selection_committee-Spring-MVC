package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.ApplicationDto;
import selection_committee.exception.FacultyNotFoundException;
import selection_committee.exception.StatementAlreadyCreateException;
import selection_committee.exception.StatementAlreadyRollbackException;
import selection_committee.mapper.ApplicationMapper;
import selection_committee.model.Application;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.ApplicationRepository;
import selection_committee.repository.FacultyRepository;
import selection_committee.service.StatementService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final FacultyRepository FR;
    private final ApplicationRepository AR;
    private final ApplicationMapper MAPPER = ApplicationMapper.INSTANCE;

    @Override
    @Transactional
    public List<ApplicationDto> create(int facultyId) {
        int places = 1;
        List<Application> list = AR.findAllByFaculty(FR.findById(facultyId).orElseThrow(FacultyNotFoundException::new));
        if (existsStatement(list)) {
            throw new StatementAlreadyCreateException();
        }
        for (Application application : list) {
            if (application.getUser().isBlockedStatus()) {
                continue;
            }
            if (places <= application.getFaculty().getBudgetQty()
                    && application.getAverageGrade() >= application.getFaculty().getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);
            } else if (places <= application.getFaculty().getBudgetQty()
                    && application.getAverageGrade() <= application.getFaculty().getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);
            } else if (places <= application.getFaculty().getTotalQty() && application.getAverageGrade() >= application.getFaculty().getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);
            } else if (places <= application.getFaculty().getTotalQty() && application.getAverageGrade() <= application.getFaculty().getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);
            } else {
                application.setApplicationStatus(ApplicationStatus.REJECTED);
            }
        }
        changeApplicationStatus(MAPPER.mapToListDto(list));
        log.info("'Statement' by facultyId : {} successfully created.", facultyId);
        return MAPPER.mapToListDto(list);
    }

    @Override
    @Transactional
    public List<ApplicationDto> rollback(int facultyId) {
        List<Application> list = AR.findAllByFaculty(FR.findById(facultyId).orElseThrow(FacultyNotFoundException::new));
        if (!existsStatement(list)) {
            throw new StatementAlreadyRollbackException();
        }
        for (Application dto : list) {
            if (dto.getApplicationStatus() == ApplicationStatus.BLOCKED) {
                continue;
            }
            dto.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
        }
        changeApplicationStatus(MAPPER.mapToListDto(list));
        log.info("'Statement' by facultyId : {} successfully rollback.", facultyId);
        return MAPPER.mapToListDto(list);
    }

    private boolean existsStatement(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (application.getApplicationStatus() == ApplicationStatus.IN_PROCESSING) {
                return false;
            }
        }
        return true;
    }

    private void changeApplicationStatus(List<ApplicationDto> list) {
        List<Thread> threads = new ArrayList<>();
        for (ApplicationDto dto : list) {
            Thread newThread = new Thread(() -> {
                dto.setId(dto.getId());
                dto.setApplicationStatus(dto.getApplicationStatus());
                AR.save(MAPPER.mapToApplication(dto));
            });
            newThread.start();
            threads.add(newThread);
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.error("Cannot close thread: " + e.getMessage());
                t.interrupt();
            }
        }
    }
}
