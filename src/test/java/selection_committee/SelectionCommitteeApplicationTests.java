package selection_committee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import selection_committee.controller.model.*;
import selection_committee.dto.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static selection_committee.test.util.TestApplicationUtil.APPLICATION_ID;
import static selection_committee.test.util.TestApplicationUtil.createApplicationDto;
import static selection_committee.test.util.TestFacultySubjectUtil.FACULTY_SUBJECT_ID;
import static selection_committee.test.util.TestFacultySubjectUtil.createFacultySubjectDto;
import static selection_committee.test.util.TestFacultyUtil.FACULTY_ID;
import static selection_committee.test.util.TestFacultyUtil.createFacultyDto;
import static selection_committee.test.util.TestGradeUtil.GRADE_ID;
import static selection_committee.test.util.TestSubjectUtil.SUBJECT_ID;
import static selection_committee.test.util.TestSubjectUtil.createSubjectDto;
import static selection_committee.test.util.TestUserUtil.USER_ID;
import static selection_committee.test.util.TestUserUtil.createUserDto;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SelectionCommitteeApplicationTests {

    @Value("http://localhost:${local.server.port}/api/committee/user/")
    private String userURL;
    @Value("http://localhost:${local.server.port}/api/committee/subject/")
    private String subjectURL;
    @Value("http://localhost:${local.server.port}/api/committee/grade/")
    private String gradeURL;
    @Value("http://localhost:${local.server.port}/api/committee/faculty/")
    private String facultyURL;
    @Value("http://localhost:${local.server.port}/api/committee/facultySubject/")
    private String facultySubjectURL;
    @Value("http://localhost:${local.server.port}/api/committee/application/")
    private String applicationURL;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void appTest() {
        UserDto userDto = createUserDto();
        UserModel userModel = testRestTemplate.postForObject(userURL, userDto, UserModel.class);
        assertEquals(userDto.getEmail(), userModel.getUserDto().getEmail());

        userModel = testRestTemplate.getForObject(userURL + userDto.getEmail(), UserModel.class);
        assertEquals(userDto.getEmail(), userModel.getUserDto().getEmail());

        SubjectDto subjectDto = createSubjectDto();
        SubjectModel subjectModel = testRestTemplate.postForObject(subjectURL, subjectDto, SubjectModel.class);
        assertEquals(subjectDto.getSubjectName(), subjectModel.getSubjectDto().getSubjectName());

        subjectModel = testRestTemplate.getForObject(subjectURL + SUBJECT_ID, SubjectModel.class);
        assertEquals(subjectDto.getSubjectName(), subjectModel.getSubjectDto().getSubjectName());

        GradeDto gradeDto = GradeDto.builder().grade(185).build();
        GradeModel createModel = testRestTemplate.postForObject(gradeURL + USER_ID + "/" + SUBJECT_ID, gradeDto, GradeModel.class);
        assertEquals(gradeDto.getGrade(), createModel.getGradeDto().getGrade());

        FacultyDto facultyDto = createFacultyDto();
        FacultyModel facultyModel = testRestTemplate.postForObject(facultyURL, facultyDto, FacultyModel.class);
        assertEquals(facultyDto.getFacultyName(), facultyModel.getFacultyDto().getFacultyName());

        facultyModel = testRestTemplate.getForObject(facultyURL + FACULTY_ID, FacultyModel.class);
        assertEquals(facultyDto.getFacultyName(), facultyModel.getFacultyDto().getFacultyName());

        FacultySubjectDto facultySubjectDto = createFacultySubjectDto();
        FacultySubjectModel facultySubjectModel = testRestTemplate.postForObject(facultySubjectURL + FACULTY_ID + "/" + SUBJECT_ID, facultySubjectDto, FacultySubjectModel.class);
        assertEquals(FACULTY_SUBJECT_ID, facultySubjectModel.getFacultySubjectDto().getId());

        ApplicationDto applicationDto = createApplicationDto();
        ApplicationModel applicationModel = testRestTemplate.postForObject(applicationURL + USER_ID + "/" + FACULTY_ID, applicationDto, ApplicationModel.class);
        assertEquals(applicationDto.getUser().getEmail(), applicationModel.getApplicationDto().getUser().getEmail());

        testRestTemplate.delete(applicationURL + APPLICATION_ID);

        testRestTemplate.delete(facultySubjectURL + FACULTY_SUBJECT_ID);

        testRestTemplate.delete(facultyURL + FACULTY_ID);

        testRestTemplate.delete(gradeURL + GRADE_ID);

        testRestTemplate.delete(subjectURL + SUBJECT_ID);

        testRestTemplate.delete(userURL + USER_ID);
    }
}
