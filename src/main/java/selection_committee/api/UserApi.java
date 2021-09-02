package selection_committee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import selection_committee.controller.model.UserModel;
import selection_committee.dto.UserDto;
import selection_committee.dto.group.OnCreate;
import selection_committee.dto.group.OnUpdate;

import java.util.List;

@Api(tags = "User API")
@RequestMapping("/api/committee/user")
public interface UserApi {

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<UserModel> getAll();

    @ApiOperation("Get user by 'email'")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    UserModel getByEmail(@PathVariable String email);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel create(@RequestBody @Validated(OnCreate.class) UserDto userDto);

    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}")
    UserModel update(@PathVariable int userId, @RequestBody @Validated(OnUpdate.class) UserDto userDto);

    @ApiOperation("Change user's blocked status")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/changeBlockedStatus/{userId}")
    UserModel changeBlockedStatus(@PathVariable int userId);

    @ApiOperation("Delete user")
    @DeleteMapping("/{userId}")
    ResponseEntity<Void> delete(@PathVariable int userId);
}
