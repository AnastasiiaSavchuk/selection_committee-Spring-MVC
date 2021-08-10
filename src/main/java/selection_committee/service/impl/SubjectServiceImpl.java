package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.SubjectDto;
import selection_committee.model.Subject;
import selection_committee.repository.SubjectRepository;
import selection_committee.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    @Override
    public List<SubjectDto> getAll() {
        log.info("getSubject list.");
        List<Subject> subjects = repository.getAll();
        return mapSubjectListToSubjectDtoList(subjects);
    }

    @Override
    public SubjectDto getById(int id) {
        log.info("getSubject by id : {}.", id);
        Subject subject = repository.getById(id);
        return mapSubjectToSubjectDto(subject);
    }

    @Override
    public SubjectDto create(SubjectDto subjectDto) {
        log.info("createSubject.");
        Subject subject = mapSubjectDtoToSubject(subjectDto);
        subject = repository.create(subject);
        return mapSubjectToSubjectDto(subject);
    }

    @Override
    public SubjectDto update(int id, SubjectDto subjectDto) {
        log.info("updateSubject with id : {}", id);
        Subject subject = mapSubjectDtoToSubject(subjectDto);
        subject = repository.update(id, subject);
        return mapSubjectToSubjectDto(subject);
    }

    @Override
    public void delete(int id) {
        log.info("deleteSubject with id : {}.", id);
        repository.delete(id);
    }

    private SubjectDto mapSubjectToSubjectDto(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .passingGrade(subject.getPassingGrade())
                .subjectName(subject.getSubjectName())
                .build();
    }

    private List<SubjectDto> mapSubjectListToSubjectDtoList(List<Subject> subjects) {
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectDtoList.add(SubjectDto.builder()
                    .id(subject.getId())
                    .passingGrade(subject.getPassingGrade())
                    .subjectName(subject.getSubjectName())
                    .build());
        }
        return subjectDtoList;
    }

    private Subject mapSubjectDtoToSubject(SubjectDto subjectDto) {
        return Subject.builder()
                .id(subjectDto.getId())
                .passingGrade(subjectDto.getPassingGrade())
                .subjectName(subjectDto.getSubjectName())
                .build();
    }
}