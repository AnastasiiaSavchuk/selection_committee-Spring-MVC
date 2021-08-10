package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.Subject;
import selection_committee.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectRepositoryImpl implements SubjectRepository {

    private final List<Subject> list = new ArrayList<>();

    @Override
    public List<Subject> getAll() {
        return list;
    }

    @Override
    public Subject getById(int id) {
        return list.stream()
                .filter(s -> s.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Subject is not found!"));
    }

    @Override
    public Subject create(Subject subject) {
        list.add(subject);
        return subject;
    }

    @Override
    public Subject update(int id, Subject subject) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            list.add(subject);
        } else {
            throw new RuntimeException("Subject is not found!");
        }
        return subject;
    }

    @Override
    public void delete(int id) {
        list.removeIf(u -> u.getId() == id);
    }
}