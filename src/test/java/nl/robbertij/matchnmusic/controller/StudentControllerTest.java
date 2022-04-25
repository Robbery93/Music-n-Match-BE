package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService mockService;


    @Test
    void getStudents() {
    }

    @Test
    void getStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void partialUpdateStudent() {
    }

    @Test
    void getLesson() {
    }

    @Test
    void unsubscribe() {
    }

    @Test
    void applyForLesson() {
    }
}