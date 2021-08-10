package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.FacultyDto;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.service.FacultySubjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacultySubjectController {

    private final FacultySubjectService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/facultySubjects")
    public List<FacultySubjectDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/facultySubjectByFaculty/{facultyId}")
    public List<FacultySubjectDto> getById(@PathVariable int facultyId) {
        return service.getAllByFacultyId(facultyId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createFacultySubject")
    public FacultySubjectDto create(@RequestBody FacultySubjectDto facultySubjectDto) {
        return service.create(facultySubjectDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateFacultySubject/{id}")
    public FacultySubjectDto update(@PathVariable int id, @RequestBody FacultySubjectDto facultySubjectDto) {
        return service.update(id, facultySubjectDto);
    }

    @DeleteMapping(value = "/deleteFacultySubject/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
