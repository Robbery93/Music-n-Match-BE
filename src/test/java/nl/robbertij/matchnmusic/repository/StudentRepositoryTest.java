package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.MatchNMusicApplication;
import nl.robbertij.matchnmusic.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {MatchNMusicApplication.class})
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void whenFindAllByNameIgnoreCase_thenReturnStudent() {

        Student student = new Student();
        student.setName("Robbert");
        student.setEmail("robbertijpelaar93@gmail.com");
        student.setInstrument("guitar");
        student.setPreferenceForLessonType("Videolessen");
        Student student1 = new Student();
        student1.setName("Anna");
        student1.setEmail("annaspeksnijder");
        student1.setInstrument("singing");
        student1.setPreferenceForLessonType("Live lessen");
        Student student2 = new Student();
        student2.setName("Bas");
        student2.setEmail("bas@gmail.com");
        student2.setInstrument("guitar");
        student2.setPreferenceForLessonType("Live lessen");
        testEntityManager.persist(student);
        testEntityManager.persist(student1);
        testEntityManager.persist(student2);
        testEntityManager.flush();

        List<Student> found = studentRepository.findAllByNameIgnoreCase("robbErt");

        String expected = "Robbert";
        String actual = found.get(0).getName();

        assertEquals(expected, actual);

    }

    @Test
    void findAllByEmail() {
        List<Student> found = studentRepository.findAllByEmail("bas@gmail.com");

        String expected = "bas@gmail.com";
        String actual = found.get(0).getEmail();

        assertEquals(expected, actual);
    }

    @Test
    void findAllByInstrument() {
        Student student = new Student();
        student.setName("Robbert");
        student.setEmail("robbertijpelaar93@gmail.com");
        student.setInstrument("guitar");
        student.setPreferenceForLessonType("Videolessen");
        Student student1 = new Student();
        student1.setName("Anna");
        student1.setEmail("annaspeksnijder");
        student1.setInstrument("singing");
        student1.setPreferenceForLessonType("Live lessen");
        Student student2 = new Student();
        student2.setName("Bas");
        student2.setEmail("bas@gmail.com");
        student2.setInstrument("guitar");
        student2.setPreferenceForLessonType("Live lessen");
        testEntityManager.persist(student);
        testEntityManager.persist(student1);
        testEntityManager.persist(student2);
        testEntityManager.flush();

        List<Student> found = studentRepository.findAllByInstrument("guitar");

        int expectedSize = 2;
        int actualSize = found.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findAllByPreferenceForLessonType() {
        Student student = new Student();
        student.setName("Robbert");
        student.setEmail("robbertijpelaar93@gmail.com");
        student.setInstrument("guitar");
        student.setPreferenceForLessonType("Videolessen");
        Student student1 = new Student();
        student1.setName("Anna");
        student1.setEmail("annaspeksnijder");
        student1.setInstrument("singing");
        student1.setPreferenceForLessonType("Live lessen");
        Student student2 = new Student();
        student2.setName("Bas");
        student2.setEmail("bas@gmail.com");
        student2.setInstrument("guitar");
        student2.setPreferenceForLessonType("Live lessen");
        testEntityManager.persist(student);
        testEntityManager.persist(student1);
        testEntityManager.persist(student2);
        testEntityManager.flush();

        List<Student> found = studentRepository.findAllByPreferenceForLessonType("Videolessen");

        int expectedSize = 1;
        int actualSize = found.size();

        assertEquals(expectedSize, actualSize);
    }
}