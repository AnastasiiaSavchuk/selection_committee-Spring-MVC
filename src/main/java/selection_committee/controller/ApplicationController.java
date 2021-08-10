package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.ApplicationDto;
import selection_committee.service.ApplicationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/applications")
    public List<ApplicationDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/applicationsByFaculty/{facultyId}")
    public List<ApplicationDto> getAllByFacultyId(@PathVariable int facultyId) {
        return service.getAllByFacultyId(facultyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/applicationsByUser/{userId}")
    public List<ApplicationDto> getAllByUserId(@PathVariable int userId) {
        return service.getAllByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/applicationById/{id}")
    public ApplicationDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createApplication")
    public ApplicationDto create(@RequestBody ApplicationDto applicationDto) {
        return service.create(applicationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateApplication/{id}")
    public ApplicationDto update(@PathVariable int id, @RequestBody ApplicationDto applicationDto) {
        return service.update(id, applicationDto);
    }

    @GetMapping(value = "/isExist/{userId}/{facultyId}")
    public ResponseEntity<Void> isExist(@PathVariable int userId, @PathVariable int facultyId) {
        service.isExist(userId, facultyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteApplication/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
