package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByEmail(String email);
    List<Teacher> findAllByInstrumentsContainingIgnoreCase(String instrument);
    List<Teacher> findAllByPreferenceForLessonType(String preference);
}
