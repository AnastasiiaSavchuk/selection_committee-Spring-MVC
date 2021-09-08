package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import selection_committee.controller.model.GradeModel;
import selection_committee.dto.GradeDto;
import selection_committee.dto.group.OnCreate;

import java.util.List;

@Api(tags = "Grade API")
@RequestMapping("/api/committee/grade")
public interface GradeApi {

    @ApiOperation("Get all grades by 'user'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    List<GradeModel> findAllGradesByUserId(@PathVariable int userId);

    @ApiOperation("Create grade")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/{subjectId}")
    GradeModel create(@PathVariable int userId, @PathVariable int subjectId, @RequestBody @Validated(OnCreate.class) GradeDto gradeDto);

    @ApiOperation("Delete grade")
    @DeleteMapping("/{gradeId}")
    ResponseEntity<Void> delete(@PathVariable int gradeId);
}
