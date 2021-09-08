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
import selection_committee.controller.asssembler.ApplicationAssembler;
import selection_committee.controller.model.ApplicationModel;
import selection_committee.dto.ApplicationDto;
import selection_committee.service.ApplicationService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static selection_committee.test.util.TestApplicationUtil.APPLICATION_ID;
import static selection_committee.test.util.TestApplicationUtil.createApplicationDto;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestUserUtil.USER_ID;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApplicationController.class)
@Import(TestConfig.class)
class ApplicationControllerTest {

    @MockBean
    private ApplicationService service;
    @MockBean
    private ApplicationAssembler assembler;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for controller : find all 'applications'")
    void getAllTest() throws Exception {
        List<ApplicationDto> dtoList = new ArrayList<>();
        dtoList.add(createApplicationDto());
        when(service.getAll()).thenReturn(dtoList);

        for (ApplicationDto dto : dtoList) {
            ApplicationModel model = new ApplicationModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/application"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : find all 'applications' by 'faculty'")
    void getAllByFacultyIdTest() throws Exception {
        List<ApplicationDto> dtoList = new ArrayList<>();
        dtoList.add(createApplicationDto());
        when(service.getAllByFacultyId(FACULTY_ID)).thenReturn(dtoList);

        for (ApplicationDto dto : dtoList) {
            ApplicationModel model = new ApplicationModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/application/byFaculty/" + FACULTY_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : find all 'applications' by 'user'")
    void getAllByUserIdTest() throws Exception {
        List<ApplicationDto> dtoList = new ArrayList<>();
        dtoList.add(createApplicationDto());
        when(service.getAllByUserId(FACULTY_ID)).thenReturn(dtoList);

        for (ApplicationDto dto : dtoList) {
            ApplicationModel model = new ApplicationModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/application/byUser/" + USER_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : create 'application'")
    void createTest() throws Exception {
        ApplicationDto dto = createApplicationDto();
        dto.setUser(null);
        dto.setFaculty(null);
        ApplicationModel model = new ApplicationModel(dto);

        when(service.create(USER_ID, FACULTY_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/committee/application/" + USER_ID + "/" + FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : delete 'application'")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/committee/application/" + APPLICATION_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
