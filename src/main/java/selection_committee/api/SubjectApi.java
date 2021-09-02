package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import selection_committee.controller.model.SubjectModel;
import selection_committee.dto.SubjectDto;
import selection_committee.dto.group.OnCreate;
import selection_committee.dto.group.OnUpdate;

import java.util.List;

@Api(tags = "Subject API")
@RequestMapping("/api/committee/subject")
public interface SubjectApi {

    @ApiOperation("Get all subjects")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<SubjectModel> getAll();

    @ApiOperation("Get subject by 'id'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{subjectId}")
    SubjectModel getById(@PathVariable int subjectId);

    @ApiOperation("Create subject")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    SubjectModel create(@RequestBody @Validated(OnCreate.class) SubjectDto subjectDto);

    @ApiOperation("Update subject")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{subjectId}")
    SubjectModel update(@PathVariable int subjectId, @RequestBody @Validated(OnUpdate.class) SubjectDto subjectDto);

    @ApiOperation("Delete subject")
    @DeleteMapping("/{subjectId}")
    ResponseEntity<Void> delete(@PathVariable int subjectId);
}
