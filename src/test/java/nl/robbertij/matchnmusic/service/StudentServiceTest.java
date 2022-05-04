package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.MatchNMusicApplication;
import nl.robbertij.matchnmusic.dto.request.StudentRequestDto;
import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import nl.robbertij.matchnmusic.model.Teacher;
import nl.robbertij.matchnmusic.repository.LessonRepository;
import nl.robbertij.matchnmusic.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @MockBean
    private LessonRepository lessonRepository;

    @Mock
    Student student;
    @Mock
    Student student2;
    @Mock
    Student student3;
    @Mock
    Student student4;


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
        student2 = new Student();
        student2.setName("Chris");
        student2.setInstrument("guitar");
        student2.setPreferenceForLessonType("Live lessen");

        student3 = new Student();
        student3.setName("Paultje");
        student3.setInstrument("piano");
        student3.setPreferenceForLessonType("Online");

        student4 = new Student();
        student4.setName("Arie");
        student4.setInstrument("keyboard");
        student4.setPreferenceForLessonType("Online");

        teacher = new Teacher();
        teacher.setId(1L);

        key = new StudentTeacherKey(1L, 1L);


        lesson = new Lesson(key, teacher, student);
        lesson.setActive(true);

        studentRequestDto.setName("Hendrik");
        studentRequestDto.setEmail("hendrik@gmail.com");
        studentRequestDto.setAge("48");
        studentRequestDto.setPhoneNumber("0612345678");
        studentRequestDto.setResidence("Den Haag");
        studentRequestDto.setInstrument("piano");
        studentRequestDto.setRequest("ik wil graag piano leren spelen");
        studentRequestDto.setPreferenceForLessonType("Live lessen");
    }

    @DisplayName("Test get all Students given no parameters")
    @Test
    void getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> found = studentService.getStudents("", "", "");

        int expected = 4;
        int actual = found.size();

        assertEquals(expected, actual);
    }

    @DisplayName("Test get all Students given instrument parameter")
    @Test
    void givenInstrument_thenReturnStudentsOfGivenInstrument() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        students.removeIf(student -> !Objects.equals(student.getInstrument(), "guitar"));

        when(studentRepository.findAllByInstrument("guitar")).thenReturn(students);

        List<Student> found = studentService.getStudents("guitar", "", "");

        int expected = 2;
        int actual = found.size();

        assertEquals(expected, actual);
        assertTrue(found.contains(student));
        assertFalse(found.contains(student4));
    }

    @DisplayName("Test get all Students given name parameter")
    @Test
    void givenName_thenReturnStudentsOfGivenName() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        students.removeIf(student -> !Objects.equals(student.getName(), "Arie"));

        when(studentRepository.findAllByNameIgnoreCase("aRie")).thenReturn(students);

        List<Student> found = studentService.getStudents("", "aRie", "");

        int expectedSize = 1;
        int actualSize = found.size();

        String expectedName = "Arie";
        String actualName = found.get(0).getName();

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedName, actualName);
    }

    @DisplayName("Test get all Students given preference parameter")
    @Test
    void givenPreference_thenReturnStudentsOfGivenPreference() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        students.removeIf(student -> !Objects.equals(student.getPreferenceForLessonType(), "Live lessen"));

        when(studentRepository.findAllByPreferenceForLessonType("Live lessen")).thenReturn(students);

        List<Student> found = studentService.getStudents("", "", "Live lessen");

        int expectedSize = 2;
        int actualSize = found.size();

        assertEquals(expectedSize, actualSize);
    }

    @DisplayName("Test get Student by id")
    @Test
    void getStudentById() {
        long id = student.getId();

        when(studentRepository.findById(id))
                .thenReturn(java.util.Optional.ofNullable(student));

        String expected = "Robbert";

        Student found = studentService.getStudentById(id);

        assertEquals(expected, found.getName());
    }

    @DisplayName("Delete Student by id")
    @Test
    void deleteStudent() {
        long id = student.getId();

        when(studentRepository.existsById(id)).thenReturn(true);

        studentService.deleteStudentById(id);

        verify(studentRepository, times(1)).existsById(id);
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

    @DisplayName("Update a Student")
    @Test
    void updateStudent() {
        student.setName("Pietertje");

        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        assertThat(student.getName()).isEqualTo("Pietertje");
        assertThat(student.getEmail()).isEqualTo("robbertijpelaar93@gmail.com");

        studentService.updateStudent(1L, student);

        verify(studentRepository, times(1)).save(student);

        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));

        Student found = studentService.getStudentById(1L);

        assertEquals("Pietertje", found.getName());
    }

    @DisplayName("Partially update Student")
    @Test
    void partialUpdateStudent() {
        student.setName("Pietertje");
        student.setEmail("pietertje@gmail.com");
        student.setPreferenceForLessonType("Videolessen");

        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));

        studentService.partialUpdateStudent(1L, student);

        verify(studentRepository, times(1)).save(student);

        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));

        Student found = studentService.getStudentById(1L);

        assertEquals("Pietertje", found.getName());
        assertEquals("pietertje@gmail.com", found.getEmail());
        assertEquals("Videolessen", found.getPreferenceForLessonType());
    }

    @DisplayName("Should return active lesson")
    @Test
    void getLessons() {
        long id = student.getId();
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);

        when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));
        when(lessonRepository.findAllByStudentId(id)).thenReturn(lessons);

        student.setLesson(lessons);

        List<Lesson> foundLesson = studentService.getLesson(id);

        assertEquals(foundLesson, lessons);
    }

    @DisplayName("Should return no applications")
    @Test
    void getApplications() {
        long id = student.getId();
        List<Lesson> applications = new ArrayList<>();
        applications.add(lesson);
        when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));
        when(lessonRepository.findAllByStudentId(id)).thenReturn(applications);

        student.setApplications(applications);

        List<Lesson> foundApplications = studentService.getApplications(id);

        int expectedNumberOfApplications = 0;
        int actualNumberOfApplications = foundApplications.size();

        assertEquals(expectedNumberOfApplications, actualNumberOfApplications);
    }

    @Test
    void getDeleteException() {
        assertThrows(RecordNotFoundException.class, () -> studentService.deleteStudentById(null));
    }
}