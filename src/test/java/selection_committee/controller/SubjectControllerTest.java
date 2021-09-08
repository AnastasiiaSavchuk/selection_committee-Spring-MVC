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
import selection_committee.controller.asssembler.SubjectAssembler;
import selection_committee.controller.model.SubjectModel;
import selection_committee.dto.SubjectDto;
import selection_committee.service.SubjectService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static selection_committee.test.util.TestSubjectUtil.SUBJECT_ID;
import static selection_committee.test.util.TestSubjectUtil.createSubjectDto;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SubjectController.class)
@Import(TestConfig.class)
class SubjectControllerTest {


    @MockBean
    private SubjectService service;
    @MockBean
    private SubjectAssembler assembler;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for controller : find all 'subjects'")
    void getAllTest() throws Exception {
        List<SubjectDto> dtoList = new ArrayList<>();
        dtoList.add(createSubjectDto());
        when(service.getAll()).thenReturn(dtoList);

        for (SubjectDto dto : dtoList) {
            SubjectModel model = new SubjectModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/subject"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : find 'subject' by 'id'")
    void getByIdTest() throws Exception {
        SubjectDto dto = createSubjectDto();
        SubjectModel model = new SubjectModel(dto);

        when(service.getById(SUBJECT_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(get("/api/committee/subject/" + SUBJECT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : create 'subject'")
    void createTest() throws Exception {
        SubjectDto dto = createSubjectDto();
        SubjectModel model = new SubjectModel(dto);

        when(service.create(dto)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/committee/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : update 'subject'")
    void updateTest() throws Exception {
        SubjectDto dto = createSubjectDto();
        SubjectModel model = new SubjectModel(dto);

        when(service.create(dto)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(patch("/api/committee/subject/" + SUBJECT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test for controller : delete 'subject'")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/committee/subject/" + SUBJECT_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
