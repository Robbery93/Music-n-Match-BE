package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import nl.robbertij.matchnmusic.model.Teacher;
import nl.robbertij.matchnmusic.repository.LessonRepository;
import nl.robbertij.matchnmusic.repository.StudentRepository;
import nl.robbertij.matchnmusic.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository,
                         StudentRepository studentRepository,
                         TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(long teacherId, long studentId) {
        return lessonRepository.findById(new StudentTeacherKey(teacherId, studentId)).orElse(null);
    }

    public void deleteLesson(long teacherId, long studentId) {
        StudentTeacherKey id = new StudentTeacherKey(teacherId, studentId);
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }


    public StudentTeacherKey createLesson(long teacherId, long studentId){
        if (!teacherRepository.existsById(teacherId)) {
            throw new RecordNotFoundException("ID does not exist");
        }
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        if (!studentRepository.existsById(studentId)) {
            throw new RecordNotFoundException("ID does not exist");
        }
        Student student = studentRepository.findById(studentId).orElse(null);

        Lesson newLesson = new Lesson();

        newLesson.setTeacher(teacher);
        newLesson.setStudent(student);

        StudentTeacherKey id = new StudentTeacherKey(teacherId, studentId);
        newLesson.setId(id);
        lessonRepository.save(newLesson);
        return id;
    }


    public void updateHomework(long teacherId, long studentId, Lesson lesson) {
        StudentTeacherKey id = new StudentTeacherKey(teacherId, studentId);
        if(!lessonRepository.existsById(id)) {
            throw new RecordNotFoundException("ID does not exist");
        }

        Lesson existingLesson = lessonRepository.findById(id).orElse(null);

        if(lesson.getHomework() != null){
            existingLesson.setHomework(lesson.getHomework());
        }

        lessonRepository.save(existingLesson);
    }
}
