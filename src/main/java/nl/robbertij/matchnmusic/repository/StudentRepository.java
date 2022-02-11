package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Iterable<Student> findAllByNameIgnoreCase(String name);
    Iterable<Student> findAllByEmail(String email);
    Iterable<Student> findAllByInstrument(String instrument);
    Iterable<Student> findAllByPreferenceForLessonType(String preference);
}
