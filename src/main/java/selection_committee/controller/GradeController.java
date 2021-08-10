package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.ApplicationGradeDto;
import selection_committee.dto.GradeDto;
import selection_committee.service.GradeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GradeController {

    private final GradeService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/grades")
    public List<GradeDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/gradesByUser/{userId}")
    public List<GradeDto> getGradeByUserId(@PathVariable int userId) {
        return service.getAllByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/gradeById/{id}")
    public GradeDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createGrade")
    public GradeDto create(@RequestBody GradeDto gradeDto) {
        return service.create(gradeDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateGrade/{id}")
    public GradeDto update(@PathVariable int id, @RequestBody GradeDto gradeDto) {
        return service.update(id, gradeDto);
    }

    @DeleteMapping(value = "/deleteGrade/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
