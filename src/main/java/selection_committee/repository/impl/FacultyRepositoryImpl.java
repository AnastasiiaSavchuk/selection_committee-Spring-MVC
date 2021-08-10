package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.Faculty;
import selection_committee.model.Subject;
import selection_committee.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class FacultyRepositoryImpl implements FacultyRepository {

    private final List<Faculty> list = new ArrayList<>();

    @Override
    public List<Faculty> getAll() {
        return list;
    }

    @Override
    public Faculty getById(int id) {
        return list.stream()
                .filter(f -> f.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Faculty is not found!"));
    }

    @Override
    public Faculty create(Faculty faculty) {
        list.add(faculty);
        return faculty;
    }

    @Override
    public Faculty update(int id, Faculty faculty) {
        boolean isDeleted = list.removeIf(f -> f.getId() == id);
        if (isDeleted) {
            list.add(faculty);
        } else {
            throw new RuntimeException("Faculty is not found!");
        }
        return faculty;
    }

    @Override
    public void delete(int id) {
        list.removeIf(f -> f.getId() == id);
    }
}
