package selection_committee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import selection_committee.api.UserApi;
import selection_committee.controller.asssembler.UserAssembler;
import selection_committee.controller.model.UserModel;
import selection_committee.dto.UserDto;
import selection_committee.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService SERVICE;
    private final UserAssembler ASSEMBLER;

    @Override
    public List<UserModel> getAll() {
        List<UserModel> userModels = new ArrayList<>();
        List<UserDto> list = SERVICE.getAll();
        for (UserDto dto : list) {
            userModels.add(ASSEMBLER.toModel(dto));
        }
        return userModels;
    }

    @Override
    public UserModel getByEmail(String email) {
        UserDto dto = SERVICE.getByEmail(email);
        return ASSEMBLER.toModel(dto);
    }

    @Override
    public UserModel create(UserDto userDto) {
        UserDto dto = SERVICE.create(userDto);
        return ASSEMBLER.toModel(dto);
    }

    @Override
    public UserModel update(int userId, UserDto userDto) {
        UserDto dto = SERVICE.update(userId, userDto);
        return ASSEMBLER.toModel(dto);
    }

    @Override
    public UserModel changeBlockedStatus(int userId) {
        UserDto dto = SERVICE.changeBlockedStatus(userId);
        return ASSEMBLER.toModel(dto);
    }

    @Override
    public ResponseEntity<Void> delete(int userId) {
        SERVICE.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
