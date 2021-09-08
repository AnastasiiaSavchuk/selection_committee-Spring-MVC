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
import selection_committee.controller.asssembler.FacultyAssembler;
import selection_committee.controller.model.FacultyModel;
import selection_committee.dto.FacultyDto;
import selection_committee.service.FacultyService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestFacultyUtil.createFacultyDto;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FacultyController.class)
@Import(TestConfig.class)
class FacultyControllerTest {

    @MockBean
    private FacultyService service;
    @MockBean
    private FacultyAssembler assembler;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for controller : find all 'faculties'")
    void getAllTest() throws Exception {
        List<FacultyDto> dtoList = new ArrayList<>();
        dtoList.add(createFacultyDto());
        when(service.getAll()).thenReturn(dtoList);

        for (FacultyDto dto : dtoList) {
            FacultyModel model = new FacultyModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/faculty"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : find 'faculty' by 'id'")
    void getByIdTest() throws Exception {
        FacultyDto dto = createFacultyDto();
        FacultyModel model = new FacultyModel(dto);

        when(service.getById(FACULTY_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(get("/api/committee/faculty/" + FACULTY_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : create 'faculty'")
    void createTest() throws Exception {
        FacultyDto dto = createFacultyDto();
        FacultyModel model = new FacultyModel(dto);

        when(service.getById(FACULTY_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/committee/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test for controller : update 'faculty'")
    void updateTest() throws Exception {
        FacultyDto dto = createFacultyDto();
        FacultyModel model = new FacultyModel(dto);

        when(service.getById(FACULTY_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(patch("/api/committee/faculty/" + FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test for controller : delete 'faculty'")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/committee/faculty/" + FACULTY_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
