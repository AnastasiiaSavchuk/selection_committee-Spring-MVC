package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.model.Application;
import selection_committee.model.Faculty;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.impl.ApplicationRepositoryImpl;
import selection_committee.service.StatementService;
import selection_committee.util.mail.MessageCreator;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {

    @Override
    public void changeApplicationStatus(List<Application> applicationList, Faculty faculty) {
        int places = 1;

        for (Application application : applicationList) {
            if (application.getUser().isBlocked()) {
                continue;
            }

            if (places <= faculty.getBudgetQty() &&
                    application.getAverageGrade() >= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);
            } else if (places <= faculty.getBudgetQty() &&
                    application.getAverageGrade() <= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);
            } else if (places <= faculty.getTotalQty() &&
                    application.getAverageGrade() >= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);
            } else if (places <= faculty.getTotalQty() &&
                    application.getAverageGrade() <= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);
            } else {
                application.setApplicationStatus(ApplicationStatus.REJECTED);
            }
        }
    }

    @Override
    public void rollbackApplicationStatus(List<Application> applicationList) {
        for (Application currentApp : applicationList) {
            if (currentApp.getApplicationStatus() == ApplicationStatus.BLOCKED) {
                continue;
            }
            currentApp.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
        }
    }

    @Override
    public boolean changeApplicationStatusByQTY(List<Application> applicationList) {
        List<Thread> threads = new ArrayList<>();
        for (Application application : applicationList) {
            Thread newThread = new Thread(() -> {
                new ApplicationRepositoryImpl().update(application.getId(), application);
            });
            newThread.start();
            threads.add(newThread);
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.error("Cannot close thread: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isExist(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (application.getApplicationStatus() == ApplicationStatus.IN_PROCESSING) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sendToEmail(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (application.getApplicationStatus() == ApplicationStatus.BUDGET_APPROVED ||
                    application.getApplicationStatus() == ApplicationStatus.CONTRACT_APPROVED) {
                MessageCreator.writeSuccessfulEnrollment(application.getUser().getEmail(),
                        application.getUser().getFirstName(), application.getUser().getLastName(),
                        application.getUser().getMiddleName(), application.getApplicationStatus(),
                        application.getFaculty().getFacultyName());
            } else {
                MessageCreator.writeUnSuccessfullyEnrollment(application.getUser().getEmail(),
                        application.getUser().getFirstName(), application.getUser().getLastName(),
                        application.getUser().getMiddleName(), application.getFaculty().getFacultyName());
            }
            new ApplicationRepositoryImpl().updateIsSent(application.getId(), application);
        }
    }

    @Override
    public boolean isSent(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (!application.isSent()) {
                return false;
            }
        }
        return true;
    }
}

