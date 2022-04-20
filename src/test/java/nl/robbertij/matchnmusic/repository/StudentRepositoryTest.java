package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
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
        Student student1 = new Student();
        student1.setName("Anna");
        testEntityManager.persist(student);
        testEntityManager.persist(student1);
        testEntityManager.flush();

        List<Student> found = studentRepository.findAllByNameIgnoreCase(student.getName());

    }

    @Test
    void findAllByEmail() {
    }

    @Test
    void findAllByInstrument() {
    }

    @Test
    void findAllByPreferenceForLessonType() {
    }
}