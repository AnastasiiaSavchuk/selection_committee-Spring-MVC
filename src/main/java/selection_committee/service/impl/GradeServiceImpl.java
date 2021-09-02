package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.GradeDto;
import selection_committee.exception.*;
import selection_committee.mapper.GradeMapper;
import selection_committee.model.Grade;
import selection_committee.model.Subject;
import selection_committee.model.User;
import selection_committee.repository.GradeRepository;
import selection_committee.repository.SubjectRepository;
import selection_committee.repository.UserRepository;
import selection_committee.service.GradeService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository GR;
    private final UserRepository UR;
    private final SubjectRepository SR;
    private final GradeMapper MAPPER = GradeMapper.INSTANCE;

    @Override
    public List<GradeDto> getAllGradesByUserId(int userId) {
        List<Grade> list = GR.findAllByUser(UR.findById(userId).orElseThrow(UserNotFoundException::new));
        if (list.isEmpty()) {
            throw new GradeListNotFoundException();
        }
        log.info("List of 'grade' by userId : {} is found.", userId);
        return MAPPER.mapToListDto(list);
    }

    @Override
    @Transactional
    public GradeDto create(int userId, int subjectId, GradeDto gradeDto) {
        User user = UR.findById(userId).orElseThrow(UserNotFoundException::new);
        Subject subject = SR.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        if (GR.existsByUserAndSubject(user, subject)) {
            throw new GradeAlreadyExistsException();
        }
        Grade grade = MAPPER.mapToGrade(gradeDto);
        grade.setUser(user);
        grade.setSubject(subject);
        grade = GR.save(grade);
        log.info("'Grade' with id : {} successfully created. ", grade.getId());
        return MAPPER.mapToGradeDto(grade);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Grade grade = GR.findById(id).orElseThrow(GradeNotFoundException::new);
        GR.delete(grade);
        log.info("'Grade' with id : {} successfully deleted. ", grade.getId());
    }
}
