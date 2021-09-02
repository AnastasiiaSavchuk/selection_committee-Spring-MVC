package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import selection_committee.controller.model.ApplicationModel;

import java.util.List;

@Api(tags = "Statement API")
@RequestMapping("/api/committee/statement")
public interface StatementApi {

    @ApiOperation("Create 'Statement' by faculty")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/create/{facultyId}")
    List<ApplicationModel> create(@PathVariable int facultyId);

    @ApiOperation("Rollback 'Statement' by faculty")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rollback/{facultyId}")
    List<ApplicationModel> rollback(@PathVariable int facultyId);
}
