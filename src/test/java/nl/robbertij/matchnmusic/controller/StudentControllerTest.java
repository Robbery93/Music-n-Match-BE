//package nl.robbertij.matchnmusic.controller;
//
//import nl.robbertij.matchnmusic.MatchNMusicApplication;
//import nl.robbertij.matchnmusic.model.Student;
//import nl.robbertij.matchnmusic.service.CustomUserDetailsService;
//import nl.robbertij.matchnmusic.service.StudentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(StudentController.class)
//class StudentControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private StudentService mockService;
//
//    @MockBean
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Mock
//    List<Student> allStudents = new ArrayList<>();
//
//    @BeforeEach
//    void setup() {
//        Student student = new Student(
//                1L,
//                "Robbert",
//                "robbertijpelaar93@gmail.com",
//                "28",
//                "0655751563",
//                "Rotterdam",
//                "guitar",
//                "Ik wil een hoop dingen leren",
//                "Live lessen",
//                null,
//                null
//        );
//        Student student1 = new Student(
//                2L,
//                "Bas",
//                "bas@gmail.com",
//                "16",
//                "0673649120",
//                "Rotterdam",
//                "guitar",
//                "Ik wil graag gitaar leren spelen",
//                "Videolessen",
//                null,
//                null
//        );
//
//        Student student2 = new Student(
//                3L,
//                "Anna",
//                "anna@gmail.com",
//                "24",
//                "Den Haag",
//                "0673649120",
//                "singing",
//                "Ik wil graag gitaar leren spelen",
//                "Videolessen",
//                null,
//                null
//        );
//
//        allStudents.add(student);
//        allStudents.add(student1);
//        allStudents.add(student2);
//    }
//
//    @DisplayName("Should return all Students")
//    @Test
//    void getAllStudentsTest() throws Exception {
//        Mockito.when(mockService.getStudents(null, null, null)).thenReturn(allStudents);
//
//        mockMvc.perform(get("/students")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)));
//
//    }
//
//    @DisplayName("Should return Students with specified instrument")
//    @Test
//    void getAllStudents() {
//    }
//
//    @Test
//    void getStudent() {
//    }
//
//    @Test
//    void deleteStudent() {
//    }
//
//    @Test
//    void addStudent() {
//    }
//
//    @Test
//    void updateStudent() {
//    }
//
//    @Test
//    void partialUpdateStudent() {
//    }
//
//    @Test
//    void getLesson() {
//    }
//
//    @Test
//    void unsubscribe() {
//    }
//
//    @Test
//    void applyForLesson() {
//    }
//}