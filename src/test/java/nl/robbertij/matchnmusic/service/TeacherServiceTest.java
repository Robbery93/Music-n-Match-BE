package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.MatchNMusicApplication;
import nl.robbertij.matchnmusic.dto.request.TeacherRequestDto;
import nl.robbertij.matchnmusic.model.Teacher;
import nl.robbertij.matchnmusic.repository.TeacherRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {MatchNMusicApplication.class})
@EnableConfigurationProperties
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Mock
    Teacher teacher;

    @Mock
    TeacherRequestDto teacherRequestDto;

    @BeforeEach
    void setup() {
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

        teacherRequestDto.setName("Hendrik");
        teacherRequestDto.setEmail("hendrik@gmail.com");
        teacherRequestDto.setAge("48");
        teacherRequestDto.setPhoneNumber("0612345678");
        teacherRequestDto.setResidence("Den Haag");
        teacherRequestDto.setInstruments("piano");
        teacherRequestDto.setDescription("Hallo, ik ben Hendrik");
        teacherRequestDto.setExperience("Ik kan heel goed piano spelen");
        teacherRequestDto.setPreferenceForLessonType("Live lessen");
    }

    @DisplayName("Get Teacher by id")
    @Test
    void getTeacherTest() {
        long id = teacher.getId();

        when(teacherRepository.findById(id))
                .thenReturn(Optional.of(teacher));

        Teacher found = teacherService.getTeacher(id);

        String expected = "Dirk";
        String actual = found.getName();

        assertEquals(expected, actual);
    }

    @DisplayName("Delete Teacher by id")
    @Test
    void deleteTeacher() {
        long id = teacher.getId();

        when(teacherRepository.existsById(id)).thenReturn(true);

        teacherService.deleteTeacher(id);

        verify(teacherRepository, times(1)).deleteById(id);
    }

    @DisplayName("Add a Teacher test")
    @Test
    void addTeacher() {
//        Teacher testTeacher = new Teacher(
//                2L,
//                "Hendrik",
//                "hendrik@gmail.com",
//                "48",
//
//        )
    }

    @Test
    void updateTeacher() {
    }

    @Test
    void partialUpdateTeacher() {
    }

    @Test
    void getLessons() {
    }
}