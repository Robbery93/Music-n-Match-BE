package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Iterable<Teacher> findAllByEmail(String email);
    Iterable<Teacher> findAllByInstrumentsContainingIgnoreCase(String instrument);
    Iterable<Teacher> findAllByPreferenceForLessonType(String preference);
}
