package selection_committee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import selection_committee.controller.asssembler.UserAssembler;
import selection_committee.controller.model.UserModel;
import selection_committee.dto.UserDto;
import selection_committee.service.UserService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static selection_committee.test.util.TestUserUtil.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserControllerTest {

    @MockBean
    private UserService service;
    @MockBean
    private UserAssembler assembler;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for controller : find all 'users'")
    void getAllTest() throws Exception {
        List<UserDto> dtoList = new ArrayList<>();
        dtoList.add(createUserDto());
        when(service.getAll()).thenReturn(dtoList);

        for (UserDto dto : dtoList) {
            UserModel model = new UserModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/user"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : find 'user' by 'email'")
    void getByEmailTest() throws Exception {
        UserDto dto = createUserDto();
        UserModel model = new UserModel(dto);

        when(service.getByEmail(EMAIL)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(get("/api/committee/user/" + EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    @DisplayName("Test for controller : create 'user'")
    void createTest() throws Exception {
        UserDto dto = createUserDto();
        UserModel model = new UserModel(dto);

        when(service.create(dto)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/committee/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : update 'user'")
    void updateTest() throws Exception {
        UserDto dto = createUserDto();
        dto.setEmail(null);
        dto.setPassword(null);
        dto.setConfirmPassword(null);
        UserModel model = new UserModel(dto);

        when(service.update(USER_ID, dto)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(patch("/api/committee/user/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : change user's 'blocked status'")
    void changeBlockedStatusTest() throws Exception {
        UserDto dto = createUserDto();
        dto.setEmail(null);
        dto.setPassword(null);
        dto.setConfirmPassword(null);
        UserModel model = new UserModel(dto);

        when(service.changeBlockedStatus(USER_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(patch("/api/committee/user/changeBlockedStatus/" + USER_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : delete 'user'")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/committee/user/" + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
