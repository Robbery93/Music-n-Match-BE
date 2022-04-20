package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByNameIgnoreCase(String name);
    List<Student> findAllByEmail(String email);
    List<Student> findAllByInstrument(String instrument);
    List<Student> findAllByPreferenceForLessonType(String preference);
}
