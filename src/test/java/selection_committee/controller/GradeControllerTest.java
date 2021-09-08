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
import selection_committee.controller.asssembler.GradeAssembler;
import selection_committee.controller.model.GradeModel;
import selection_committee.dto.GradeDto;
import selection_committee.service.GradeService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static selection_committee.test.util.TestGradeUtil.GRADE_ID;
import static selection_committee.test.util.TestGradeUtil.createGradeDto;
import static selection_committee.test.util.TestSubjectUtil.SUBJECT_ID;
import static selection_committee.test.util.TestUserUtil.USER_ID;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GradeController.class)
@Import(TestConfig.class)
class GradeControllerTest {

    @MockBean
    private GradeService service;
    @MockBean
    private GradeAssembler assembler;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for controller : find all 'grades' by 'user'")
    void getAllTest() throws Exception {
        List<GradeDto> dtoList = new ArrayList<>();
        dtoList.add(createGradeDto());
        when(service.getAllGradesByUserId(USER_ID)).thenReturn(dtoList);

        for (GradeDto dto : dtoList) {
            GradeModel model = new GradeModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/grade/" + USER_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : create 'grade'")
    void createTest() throws Exception {
        GradeDto dto = createGradeDto();
        dto.setUser(null);
        dto.setSubject(null);
        GradeModel model = new GradeModel(dto);

        when(service.create(USER_ID, SUBJECT_ID, dto)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/committee/grade/" + USER_ID + "/" + SUBJECT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : delete 'grade'")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/committee/grade/" + GRADE_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
