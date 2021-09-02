package selection_committee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selection_committee.dto.FacultyDto;
import selection_committee.exception.FacultyAlreadyExistsException;
import selection_committee.exception.FacultyListNotFoundException;
import selection_committee.exception.FacultyNotFoundException;
import selection_committee.mapper.FacultyMapper;
import selection_committee.model.Faculty;
import selection_committee.repository.FacultyRepository;
import selection_committee.service.FacultyService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository FR;
    private final FacultyMapper MAPPER = FacultyMapper.INSTANCE;

    @Override
    public List<FacultyDto> getAll() {
        List<Faculty> list = FR.findAll();
        if (list.isEmpty()) {
            throw new FacultyListNotFoundException();
        }
        log.info("List of 'faculty' is found.");
        return MAPPER.mapToListDto(list);
    }

    @Override
    public FacultyDto getById(int id) {
        Faculty faculty = FR.findById(id).orElseThrow(FacultyNotFoundException::new);
        log.info("'Faculty' by id : {} is found.", faculty.getId());
        return MAPPER.mapToFacultyDto(faculty);
    }

    @Override
    @Transactional
    public FacultyDto create(FacultyDto facultyDto) {
        if (FR.existsById(facultyDto.getId())) {
            throw new FacultyAlreadyExistsException();
        }
        Faculty faculty = MAPPER.mapToFaculty(facultyDto);
        faculty = FR.save(faculty);
        log.info("'Faculty' with id : {} successfully created. ", faculty.getId());
        return MAPPER.mapToFacultyDto(faculty);
    }

    @Override
    @Transactional
    public FacultyDto update(int id, FacultyDto facultyDto) {
        Faculty faculty = MAPPER.mapToFaculty(facultyDto);
        faculty.setId(id);
        faculty = FR.save(faculty);
        log.info("'Faculty' with id : {} successfully updated. ", faculty.getId());
        return MAPPER.mapToFacultyDto(faculty);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Faculty faculty = FR.findById(id).orElseThrow(FacultyNotFoundException::new);
        FR.delete(faculty);
        log.info("'Faculty' with id : {} successfully deleted. ", faculty.getId());
    }
}
