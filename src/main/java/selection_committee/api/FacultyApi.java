package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import selection_committee.controller.model.FacultyModel;
import selection_committee.dto.FacultyDto;
import selection_committee.dto.group.OnCreate;
import selection_committee.dto.group.OnUpdate;

import java.util.List;

@Api(tags = "Faculty API")
@RequestMapping("/api/committee/faculty")
public interface FacultyApi {

    @ApiOperation("Get all faculties")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<FacultyModel> getAll();

    @ApiOperation("Get faculty by 'id'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{facultyId}")
    FacultyModel getById(@PathVariable int facultyId);

    @ApiOperation("Create faculty")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    FacultyModel create(@RequestBody @Validated(OnCreate.class) FacultyDto facultyDto);

    @ApiOperation("Update faculty")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{facultyId}")
    FacultyModel update(@PathVariable int facultyId, @RequestBody @Validated(OnUpdate.class) FacultyDto facultyDto);

    @ApiOperation("Delete faculty")
    @DeleteMapping("/{facultyId}")
    ResponseEntity<Void> delete(@PathVariable int facultyId);
}
