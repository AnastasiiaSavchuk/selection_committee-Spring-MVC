package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.Grade;
import selection_committee.repository.GradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradeRepositoryImpl implements GradeRepository {

    private final List<Grade> list = new ArrayList<>();

    @Override
    public List<Grade> getAll() {
        return list;
    }

    @Override
    public List<Grade> getAllByUserId(int userId) {
        return list.stream()
                .filter(g -> g.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Grade getById(int id) {
        return list.stream()
                .filter(g -> g.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Grade is not found!"));
    }

    @Override
    public Grade create(Grade grade) {
        list.add(grade);
        return grade;
    }

    @Override
    public Grade update(int id, Grade grade) {
        boolean isDeleted = list.removeIf(g -> g.getId() == id);
        if (isDeleted) {
            list.add(grade);
        } else {
            throw new RuntimeException("Grade is not found!");
        }
        return grade;
    }

    @Override
    public void delete(int id) {
        list.removeIf(g -> g.getId() == id);
    }
}
