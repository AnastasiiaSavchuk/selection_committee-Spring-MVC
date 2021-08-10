package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.SubjectDto;
import selection_committee.service.SubjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/subjects")
    public List<SubjectDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/subjectById/{id}")
    public SubjectDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createSubject")
    public SubjectDto create(@RequestBody SubjectDto subjectDto) {
        return service.create(subjectDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateSubject/{id}")
    public SubjectDto update(@PathVariable int id, @RequestBody SubjectDto subjectDto) {
        return service.update(id, subjectDto);
    }

    @DeleteMapping(value = "/deleteSubject/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
