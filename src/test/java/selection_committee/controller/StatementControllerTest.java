package selection_committee.controller;

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
import selection_committee.controller.asssembler.StatementAssembler;
import selection_committee.controller.model.ApplicationModel;
import selection_committee.dto.ApplicationDto;
import selection_committee.model.enums.ApplicationStatus;
import selection_committee.service.StatementService;
import selection_committee.test.config.TestConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static selection_committee.test.util.TestApplicationUtil.createApplicationDto;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatementController.class)
@Import(TestConfig.class)
class StatementControllerTest {

    @MockBean
    private StatementService service;
    @MockBean
    private StatementAssembler assembler;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test for controller : create 'statement'")
    void createTest() throws Exception {
        ApplicationDto app1 = createApplicationDto();

        ApplicationDto app2 = createApplicationDto();
        app2.setId(2);
        app2.setAverageGrade(141);

        ApplicationDto app3 = createApplicationDto();
        app3.setId(3);
        app3.setAverageGrade(142);

        ApplicationDto app4 = createApplicationDto();
        app4.setId(4);
        app4.setAverageGrade(143);

        ApplicationDto app5 = createApplicationDto();
        app5.setId(5);
        app5.setAverageGrade(139);

        ApplicationDto app6 = createApplicationDto();
        app6.setId(6);
        app6.setApplicationStatus(ApplicationStatus.BLOCKED);

        List<ApplicationDto> dtoList = new ArrayList<>(Arrays.asList(app1, app2, app3, app4, app5, app6));
        when(service.create(FACULTY_ID)).thenReturn(dtoList);

        for (ApplicationDto dto : dtoList) {
            ApplicationModel model = new ApplicationModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/statement/create/" + FACULTY_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test for controller : rollback 'statement'")
    void rollbackTest() throws Exception {
        ApplicationDto app1 = createApplicationDto();
        app1.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);

        ApplicationDto app2 = createApplicationDto();
        app2.setId(2);
        app2.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);

        ApplicationDto app3 = createApplicationDto();
        app3.setId(3);
        app3.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);

        ApplicationDto app4 = createApplicationDto();
        app4.setId(4);
        app4.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);

        ApplicationDto app5 = createApplicationDto();
        app5.setId(5);
        app5.setApplicationStatus(ApplicationStatus.REJECTED);

        ApplicationDto app6 = createApplicationDto();
        app6.setId(6);
        app6.setApplicationStatus(ApplicationStatus.BLOCKED);

        List<ApplicationDto> dtoList = new ArrayList<>(Arrays.asList(app1, app2, app3, app4, app5, app6));
        when(service.rollback(FACULTY_ID)).thenReturn(dtoList);

        for (ApplicationDto dto : dtoList) {
            ApplicationModel model = new ApplicationModel(dto);
            when(assembler.toModel(dto)).thenReturn(model);
        }
        mockMvc.perform(get("/api/committee/statement/rollback/" + FACULTY_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
