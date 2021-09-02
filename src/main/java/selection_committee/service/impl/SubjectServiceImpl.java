package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.SubjectDto;
import selection_committee.exception.SubjectAlreadyExistsException;
import selection_committee.exception.SubjectListNotFoundException;
import selection_committee.exception.SubjectNotFoundException;
import selection_committee.mapper.SubjectMapper;
import selection_committee.model.Subject;
import selection_committee.repository.FacultySubjectRepository;
import selection_committee.repository.SubjectRepository;
import selection_committee.service.SubjectService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository FR;
    private final FacultySubjectRepository FSR;
    private final SubjectMapper MAPPER = SubjectMapper.INSTANCE;

    @Override
    public List<SubjectDto> getAll() {
        List<Subject> list = FR.findAll();
        if (list.isEmpty()) {
            throw new SubjectListNotFoundException();
        }
        log.info("List of 'subjects' is found.");
        return MAPPER.mapToListDto(list);
    }

    @Override
    public SubjectDto getById(int id) {
        Subject subject = FR.findById(id).orElseThrow(SubjectNotFoundException::new);
        log.info("'Subject' by id : {} is found.", subject.getId());
        return MAPPER.mapToSubjectDto(subject);
    }

    @Override
    @Transactional
    public SubjectDto create(SubjectDto subjectDto) {
        if (FR.existsById(subjectDto.getId())) {
            throw new SubjectAlreadyExistsException();
        }
        Subject subject = MAPPER.mapToSubject(subjectDto);
        subject = FR.save(subject);
        log.info("'Subject' with id : {} successfully created. ", subject.getId());
        return MAPPER.mapToSubjectDto(subject);
    }

    @Override
    @Transactional
    public SubjectDto update(int id, SubjectDto subjectDto) {
        Subject subject = MAPPER.mapToSubject(subjectDto);
        subject.setId(id);
        subject = FR.save(subject);
        log.info("'Subject' with id : {} successfully updated.", subject.getId());
        return MAPPER.mapToSubjectDto(subject);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Subject subject = FR.findById(id).orElseThrow(SubjectNotFoundException::new);
        FR.delete(subject);
        log.info("'Subject' with id : {} successfully deleted.", id);
    }
}