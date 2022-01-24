package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.dto.request.TeacherRequestDto;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import nl.robbertij.matchnmusic.model.Teacher;
import nl.robbertij.matchnmusic.service.LessonService;
import nl.robbertij.matchnmusic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LessonService lessonService;

    // Endpoints for teachers

    @GetMapping
    public ResponseEntity<Object> getTeachers(@RequestParam(name = "instrument", defaultValue = "")String instrument,
                                              @RequestParam(name = "pref", defaultValue = "") String preference) {
        return ResponseEntity.ok(teacherService.getTeachers(instrument, preference));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> addTeacher(@Valid @RequestBody TeacherRequestDto teacherRequestDto) {
        Long newId = teacherService.addTeacher(teacherRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Object> partialUpdateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        teacherService.partialUpdateTeacher(id, teacher);
        return ResponseEntity.noContent().build();
    }

    // Endpoints for lessons

    @GetMapping(path = "/{id}/lessons")
    public ResponseEntity<Object> getLessons(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teacherService.getLessons(id));
    }

    @GetMapping(path = "/{teacher_id}/lessons/{student_id}")
    public ResponseEntity<Object> getLessonByStudent(@PathVariable(name = "teacher_id") long teacherId,
                                                     @PathVariable(name = "student_id") long studentId) {
        return ResponseEntity.ok(lessonService.getLessonById(teacherId, studentId));
    }

    @PostMapping(path = "/{teacher_id}/lessons/{student_id}")
    public ResponseEntity<Object> addLesson(@PathVariable(name = "teacher_id") long teacherId,
                                            @PathVariable(name = "student_id") long studentId,
                                            @RequestBody Lesson lesson) {
        StudentTeacherKey newId = lessonService.createLesson(teacherId, studentId, lesson);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(path = "{teacher_id}/lessons/{student_id}")
    public ResponseEntity<Object> updateHomework(@PathVariable(name = "teacher_id") long teacherId,
                                                 @PathVariable(name = "student_id") long studentId,
                                                 @RequestBody Lesson lesson) {
        lessonService.updateHomework(teacherId, studentId, lesson);
        return ResponseEntity.noContent().build();
    }

}
