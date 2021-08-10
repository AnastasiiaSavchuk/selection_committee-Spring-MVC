package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.FacultyDto;
import selection_committee.service.FacultyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/faculties")
    public List<FacultyDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/facultyById/{id}")
    public FacultyDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createFaculty")
    public FacultyDto create(@RequestBody FacultyDto facultyDto) {
        return service.create(facultyDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateFaculty/{id}")
    public FacultyDto update(@PathVariable int id, @RequestBody FacultyDto facultyDto) {
        return service.update(id, facultyDto);
    }

    @DeleteMapping(value = "/deleteFaculty/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
