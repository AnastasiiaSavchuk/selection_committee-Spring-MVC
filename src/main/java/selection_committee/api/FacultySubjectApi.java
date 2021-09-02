package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.controller.model.FacultySubjectModel;

import java.util.List;

@Api(tags = "FacultySubject API")
@RequestMapping("/api/committee/facultySubject")
public interface FacultySubjectApi {

    @ApiOperation("Get all subjects by 'faculty'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{facultyId}")
    List<FacultySubjectModel> getAllByByFacultyId(@PathVariable int facultyId);

    @ApiOperation("Put subject to faculty")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{facultyId}/{subjectId}")
    FacultySubjectModel create(@PathVariable int facultyId, @PathVariable int subjectId);

    @ApiOperation("Delete subject from faculty")
    @DeleteMapping("/{facultySubjectId}")
    ResponseEntity<Void> delete(@PathVariable int facultySubjectId);
}
