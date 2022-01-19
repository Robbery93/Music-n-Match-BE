package nl.robbertij.musicnmatch.repository;

import nl.robbertij.musicnmatch.model.Student;
import nl.robbertij.musicnmatch.model.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Iterable<Teacher> findAllByEmail(String email);
    Iterable<Teacher> findAllByInstrumentsContainingIgnoreCase(String instrument);
    Iterable<Teacher> findAllByPreferenceForLessonType(String preference);
}
