package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.ApplicationDto;
import selection_committee.dto.ApplicationGradeDto;
import selection_committee.model.ApplicationGrade;
import selection_committee.service.ApplicationGradeService;
import selection_committee.service.ApplicationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationGradeController {

    private final ApplicationGradeService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/applicationGrades")
    public List<ApplicationGradeDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/gradesByApplication/{applicationId}")
    public List<ApplicationGradeDto> getGradeByApplicationId(@PathVariable int applicationId) {
        return service.getGradeByApplicationId(applicationId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createApplicationGrade")
    public ApplicationGradeDto create(@RequestBody ApplicationGradeDto applicationGradeDto) {
        return service.create(applicationGradeDto);
    }

    @DeleteMapping(value = "/deleteApplicationGrade/{gradeId}")
    public ResponseEntity<Void> delete(@PathVariable int gradeId) {
        service.delete(gradeId);
        return ResponseEntity.noContent().build();
    }
}
