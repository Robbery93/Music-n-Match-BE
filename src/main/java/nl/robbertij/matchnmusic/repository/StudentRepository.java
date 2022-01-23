package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Iterable<Student> findAllByEmail(String email);
    Iterable<Student> findAllByInstrument(String instrument);
    Iterable<Student> findAllByResidence(String residence);
    Iterable<Student> findAllByPreferenceForLessonType(String preference);
    Iterable<Student> findAllByNameIgnoreCase(String name);
}
