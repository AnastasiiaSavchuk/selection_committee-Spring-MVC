package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.FacultySubject;
import selection_committee.repository.FacultySubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FacultySubjectRepositoryImpl implements FacultySubjectRepository {

    private final List<FacultySubject> list = new ArrayList<>();

    @Override
    public List<FacultySubject> getAll() {
        return list;
    }

    @Override
    public List<FacultySubject> getAllByFacultyId(int facultyId) {
        return list.stream().filter(fs -> fs.getFaculty().getId() == facultyId).collect(Collectors.toList());
    }

    @Override
    public FacultySubject getById(int subjectId) {
        return null;
    }

    @Override
    public FacultySubject create(FacultySubject facultySubject) {
        list.add(facultySubject);
        return facultySubject;
    }

    @Override
    public FacultySubject update(int subjectId, FacultySubject facultySubject) {
        boolean isDeleted = list.removeIf(fs -> fs.getSubject().getId() == subjectId);
        if (isDeleted) {
            list.add(facultySubject);
        } else {
            throw new RuntimeException("FacultySubject is not found!");
        }
        return facultySubject;
    }

    @Override
    public void delete(int subjectId) {
        list.removeIf(fs -> fs.getSubject().getId() == subjectId);
    }
}
