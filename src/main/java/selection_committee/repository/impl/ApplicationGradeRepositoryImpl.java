package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.ApplicationGrade;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.repository.ApplicationGradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationGradeRepositoryImpl implements ApplicationGradeRepository {

    private final List<ApplicationGrade> list = new ArrayList<>();

    @Override
    public List<ApplicationGrade> getGradeByApplicationId(int applicationId) {
        return list.stream().filter(a -> a.getApplication().getId() == applicationId).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationGrade> getAll() {
        return list;
    }

    @Override
    public ApplicationGrade getById(int id) {
        return null;
    }

    @Override
    public ApplicationGrade create(ApplicationGrade applicationGrade) {
        applicationGrade.getApplication().setApplicationStatus(ApplicationStatus.IN_PROCESSING);
        applicationGrade.getApplication().setSent(false);
        list.add(applicationGrade);
        return applicationGrade;
    }

    @Override
    public ApplicationGrade update(int id, ApplicationGrade applicationGrade) {
        return null;
    }

    @Override
    public void delete(int gradeId) {
        list.removeIf(a -> a.getGrade().getId() == gradeId);
    }
}
