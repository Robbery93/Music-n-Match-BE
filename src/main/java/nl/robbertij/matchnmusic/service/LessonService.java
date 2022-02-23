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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        return lessonRepository.findById(new StudentTeacherKey(studentId, teacherId)).orElse(null);
    }

    public Collection<Lesson> getApplications(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isEmpty()) {
            throw new RecordNotFoundException("ID does not exist");
        }

        Collection<Lesson> applications = new HashSet<>();
        Collection<Lesson> allLessons = optionalTeacher.get().getLessons();
        for (Lesson lesson : allLessons) {
            if (!lesson.isActive()) {
                applications.add(lesson);
            }
        }
        return applications;
    }

    public Collection<Lesson> getActiveLessons(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isEmpty()) {
            throw new RecordNotFoundException("ID does not exist");
        }

        Collection<Lesson> activeLessons = new HashSet<>();
        Collection<Lesson> allLessons = optionalTeacher.get().getLessons();
        for (Lesson lesson : allLessons) {
            if (lesson.isActive()) {
                activeLessons.add(lesson);
            }
        }
        return activeLessons;
    }

    public void deleteLesson(long studentId, long teacherId) {
        StudentTeacherKey id = new StudentTeacherKey(studentId, teacherId);
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }


    public StudentTeacherKey createLesson(long studentId, long teacherId){
        if (!studentRepository.existsById(studentId)) {
            throw new RecordNotFoundException("ID does not exist");
        }
        Student student = studentRepository.findById(studentId).orElse(null);

        if (!teacherRepository.existsById(teacherId)) {
            throw new RecordNotFoundException("ID does not exist");
        }
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        Lesson newLesson = new Lesson();

        newLesson.setStudent(student);
        newLesson.setTeacher(teacher);

        StudentTeacherKey id = new StudentTeacherKey(studentId, teacherId);
        newLesson.setId(id);
        lessonRepository.save(newLesson);
        return id;
    }


    public void updateHomework(long studentId, long teacherId, Lesson lesson) {
        StudentTeacherKey id = new StudentTeacherKey(studentId, teacherId);
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
