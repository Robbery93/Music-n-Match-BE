package nl.robbertij.musicnmatch.repository;

import nl.robbertij.musicnmatch.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Iterable<Student> findAllByEmail(String email);
    Iterable<Student> findAllByInstrument(String instrument);
    Iterable<Student> findAllByResidence(String residence);
    Iterable<Student> findAllByPreferenceForLessonType(String preference);
}
