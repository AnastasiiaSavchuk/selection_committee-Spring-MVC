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
import selection_committee.controller.asssembler.FacultySubjectAssembler;
import selection_committee.controller.model.FacultySubjectModel;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.service.FacultySubjectService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static selection_committee.test.util.TestFacultySubjectUtil.FACULTY_SUBJECT_ID;
import static selection_committee.test.util.TestFacultySubjectUtil.createFacultySubjectDto;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestSubjectUtil.SUBJECT_ID;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FacultySubjectController.class)
@Import(TestConfig.class)
class FacultySubjectControllerTest {

    @MockBean
    private FacultySubjectService service;
    @MockBean
    private FacultySubjectAssembler assembler;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for controller : find all 'subjects' by 'faculty'")
    void getAllTest() throws Exception {
        List<FacultySubjectDto> dtoList = new ArrayList<>();
        dtoList.add(createFacultySubjectDto());
        when(service.getAllByByFacultyId(FACULTY_ID)).thenReturn(dtoList);

        for (FacultySubjectDto dto : dtoList) {
            FacultySubjectModel model = new FacultySubjectModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/facultySubject/" + FACULTY_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : add 'subject' to the 'faculty'")
    void createTest() throws Exception {
        FacultySubjectDto dto = createFacultySubjectDto();
        dto.setFaculty(null);
        dto.setSubject(null);
        FacultySubjectModel model = new FacultySubjectModel(dto);

        when(service.create(FACULTY_ID, SUBJECT_ID)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/committee/facultySubject/" + FACULTY_ID + "/" + SUBJECT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : delete 'subject' from 'faculty'")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/committee/facultySubject/" + FACULTY_SUBJECT_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
