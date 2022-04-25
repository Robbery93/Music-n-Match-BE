package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.MatchNMusicApplication;
import nl.robbertij.matchnmusic.dto.request.StudentRequestDto;
import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import nl.robbertij.matchnmusic.model.Teacher;
import nl.robbertij.matchnmusic.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {MatchNMusicApplication.class})
@EnableConfigurationProperties
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Mock
    Student student;

    @Mock
    Teacher teacher;

    @Mock
    StudentTeacherKey key;

    @Mock
    Lesson lesson;

    @Mock
    StudentRequestDto studentRequestDto;

    @BeforeEach
    void setup() {
        student = new Student(
                1L,
                "Robbert",
                "robbertijpelaar93@gmail.com",
                "28",
                "0655751563",
                "Rotterdam",
                "guitar",
                "Ik wil een hoop dingen leren",
                "Live lessen",
                null,
                null
        );

        teacher = new Teacher (
                1L,
                "Dirk",
                "dirk@gmail.com",
                "35",
                "0698765432",
                "Den Haag",
                "piano",
                "Hallooo",
                "Super veel",
                "live lessen",
                null,
                null
        );

        key = new StudentTeacherKey(1L, 1L);

        lesson = new Lesson(key, teacher, student, "Voor nu nog niks");

        studentRequestDto.setName("Hendrik");
        studentRequestDto.setEmail("hendrik@gmail.com");
        studentRequestDto.setAge("48");
        studentRequestDto.setPhoneNumber("0612345678");
        studentRequestDto.setResidence("Den Haag");
        studentRequestDto.setInstrument("piano");
        studentRequestDto.setRequest("ik wil graag piano leren spelen");
        studentRequestDto.setPreferenceForLessonType("Live lessen");
    }

    @DisplayName("Test get Student by id")
    @Test
    void testGetStudentById() {
        long id = student.getId();

        when(studentRepository.findById(id))
                .thenReturn(java.util.Optional.ofNullable(student));

        String expected = "Robbert";

        Student found = studentService.getStudentById(id);

        assertEquals(expected, found.getName());
    }

    @DisplayName("Delete Student by id")
    @Test
    void deleteStudentTest() {
        long id = student.getId();

        when(studentRepository.existsById(id)).thenReturn(true);

        studentService.deleteStudentById(id);

        verify(studentRepository, times(1)).deleteById(id);
    }

    @DisplayName("Add a Student Test")
    @Test
    void addStudent() {
        // same as studentRequestDto
        Student testStudent = new Student(
                2L,
                "Hendrik",
                "hendrik@gmail.com",
                "48",
                "0612345678",
                "Den Haag",
                "piano",
                "Ik wil graag piano leren spelen",
                "Live lessen",
                null,
                null
        );

       when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

       Long newId = studentService.addStudent(studentRequestDto);

       when(studentRepository.findById(newId)).thenReturn(Optional.of(testStudent));

       Student found = studentService.getStudentById(newId);

       String expected = "48";
       String actual = found.getAge();

       assertEquals(expected, actual);
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

    @Test
    void getDeleteException() {
        assertThrows(RecordNotFoundException.class, () -> studentService.deleteStudentById(null));
    }
}