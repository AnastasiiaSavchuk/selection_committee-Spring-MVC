package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.controller.model.ApplicationModel;

import java.util.List;

@Api(tags = "Application API")
@RequestMapping("/api/committee/application")
public interface ApplicationApi {

    @ApiOperation("Get all applications")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<ApplicationModel> getAll();

    @ApiOperation("Get all applications by 'faculty'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byFaculty/{facultyId}")
    List<ApplicationModel> getAllByFacultyId(@PathVariable int facultyId);

    @ApiOperation("Get all applications by 'user'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byUser/{userId}")
    List<ApplicationModel> getAllByUserId(@PathVariable int userId);

    @ApiOperation("Create application")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/{facultyId}")
    ApplicationModel create(@PathVariable int userId, @PathVariable int facultyId);

    @ApiOperation("Delete application")
    @DeleteMapping("/{applicationId}")
    ResponseEntity<Void> delete(@PathVariable int applicationId);
}
