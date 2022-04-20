package nl.robbertij.matchnmusic.repository;


import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, StudentTeacherKey> {

    List<Lesson> findAllByTeacherId(long teacherId);
    List<Lesson> findAllByStudentId(long studentId);
    List<Lesson> findAllByActive(boolean active);
}
