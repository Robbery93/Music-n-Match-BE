package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {StudentService.class, StudentRepository.class})
@EnableConfigurationProperties
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Mock
    Student student;

    @Test
    void getStudents() {

    }

    @Test
    void testGetStudentById() {
        student = new Student(
                1L,
                "Robbert",
                "robbertijpelaar93@gmail.com",
                "28",
                "0655751563",
                "Rotterdam",
                "Gitaar",
                "Ik wil een hoop dingen leren",
                "Live lessen",
                null,
                null
        );


                when(studentRepository.findById(1L))
                .thenReturn(java.util.Optional.of(student));

        Long id = 1L;
        String expected = "Robbert";

        Student found = studentService.getStudentById(id);

        assertEquals(expected, found.getName());



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
    void getLessons() {
    }
}