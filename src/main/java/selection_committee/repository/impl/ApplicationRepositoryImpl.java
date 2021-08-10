package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.Application;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final List<Application> list = new ArrayList<>();

    @Override
    public List<Application> getAll() {
        return list;
    }

    @Override
    public List<Application> getAllByFacultyId(int facultyId) {
        return list.stream()
                .filter(a -> a.getFaculty().getId() == facultyId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Application> getAllByUserId(int userId) {
        return list.stream()
                .filter(a -> a.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Application getById(int id) {
        return list.stream()
                .filter(a -> a.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Application is not found!"));
    }

    @Override
    public Application create(Application application) {
        application.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
        application.setSent(false);
        list.add(application);
        return application;
    }


    @Override
    public Application update(int id, Application application) {
        boolean isDeleted = list.removeIf(a -> a.getId() == id);
        if (isDeleted) {
            list.add(application);
        } else {
            throw new RuntimeException("Application is not found!");
        }
        return application;
    }

    @Override
    public void updateIsSent(int applicationId, Application application) {
        boolean isDeleted = list.removeIf(a -> a.getId() == applicationId);
        if (isDeleted) {
            application.setSent(true);
            list.add(application);
        } else {
            throw new RuntimeException("Application is not found!");
        }
    }

    @Override
    public boolean isExist(int userId, int facultyId) {
        Optional<Application> application = list.stream()
                .filter(a -> a.getUser().getId() == userId && a.getFaculty().getId() == facultyId)
                .findAny();
        return application.isPresent();
    }

    @Override
    public void delete(int id) {
        list.removeIf(a -> a.getId() == id);
    }
}
