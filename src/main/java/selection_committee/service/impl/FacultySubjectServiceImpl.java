package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.exception.*;
import selection_committee.mapper.FacultySubjectMapper;
import selection_committee.model.Faculty;
import selection_committee.model.FacultySubject;
import selection_committee.model.Subject;
import selection_committee.repository.FacultyRepository;
import selection_committee.repository.FacultySubjectRepository;
import selection_committee.repository.SubjectRepository;
import selection_committee.service.FacultySubjectService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultySubjectServiceImpl implements FacultySubjectService {

    private final FacultySubjectRepository FSR;
    private final FacultyRepository FR;
    private final SubjectRepository SR;
    private final FacultySubjectMapper MAPPER = FacultySubjectMapper.INSTANCE;

    @Override
    public List<FacultySubjectDto> getAllByByFacultyId(int facultyId) {
        Faculty faculty = FR.findById(facultyId).orElseThrow(FacultyNotFoundException::new);

        List<FacultySubject> list = FSR.findAllByFaculty(faculty);
        if (list.isEmpty()) {
            throw new FacultySubjectListNotFoundException();
        }
        log.info("List of 'subject' by facultyId is found.");
        return MAPPER.mapToListDto(list);
    }

    @Override
    public FacultySubjectDto getById(int id) {
        FacultySubject facultySubject = FSR.findById(id).orElseThrow(FacultySubjectNotFoundException::new);
        log.info("'FacultySubject' by id : {} is found.", id);
        return MAPPER.mapToFacultySubjectDto(facultySubject);
    }

    @Override
    @Transactional
    public FacultySubjectDto create(int facultyId, int subjectId) {
        Faculty faculty = FR.findById(facultyId).orElseThrow(FacultyNotFoundException::new);
        Subject subject = SR.findById(subjectId).orElseThrow(SubjectNotFoundException::new);

        if (FSR.existsByFacultyAndSubject(faculty, subject)) {
            throw new FacultySubjectAlreadyExistsException();
        }
        FacultySubject facultySubject = FacultySubject.builder().faculty(faculty).subject(subject).build();
        facultySubject = FSR.save(facultySubject);
        updateAveragePassingGrade(faculty);
        log.info("'Subject' with id : {} successfully added to the faculty.", subjectId);
        return MAPPER.mapToFacultySubjectDto(facultySubject);
    }

    @Override
    @Transactional
    public void delete(int id) {
        FacultySubject facultySubject = FSR.findById(id).orElseThrow(FacultySubjectNotFoundException::new);
        FSR.delete(facultySubject);
        log.info("'Subject' with id : {} successfully deleted from faculty. ", facultySubject.getSubject().getId());
    }

    private void updateAveragePassingGrade(Faculty faculty) {
        int sum = 0;
        for (FacultySubject facultySubject : FSR.findAllByFaculty(faculty)) {
            sum += facultySubject.getSubject().getPassingGrade();
        }
        faculty.setAveragePassingGrade(sum / FSR.findAllByFaculty(faculty).size());
        FR.save(faculty);
    }
}
