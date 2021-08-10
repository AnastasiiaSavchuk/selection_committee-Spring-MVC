package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selection_committee.dto.UserDto;
import selection_committee.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/userByEmail/{email}")
    public UserDto getByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/userById/{id}")
    public UserDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createUser")
    public UserDto create(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateUser/{id}")
    public UserDto update(@PathVariable int id, @RequestBody UserDto userDto) {
        return service.update(id, userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/updateByAdmin/{id}")
    public UserDto updateByAdmin(@PathVariable int id, @RequestBody UserDto userDto) {
        return service.update(id, userDto);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
