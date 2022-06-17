package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.dto.request.TeacherRequestDto;
import nl.robbertij.matchnmusic.model.Lesson;
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
    private final TeacherService teacherService;
    private final LessonService lessonService;

    @Autowired
    public TeacherController(TeacherService teacherService,
                             LessonService lessonService) {
        this.teacherService = teacherService;
        this.lessonService = lessonService;
    }

    // Endpoints for teachers

    @GetMapping(path = "/all")
    public ResponseEntity<Object> getTeachers(@RequestParam(name = "instrument", defaultValue = "")String instrument,
                                              @RequestParam(name = "pref", defaultValue = "") String preference) {
        return ResponseEntity.ok(teacherService.getTeachers(instrument, preference));
    }

    @GetMapping(path = "")
    public ResponseEntity<Object> getStudentsByInstrumentAndPreference(@RequestParam(name = "instrument") String instrument,
                                                                       @RequestParam(name = "preference") String preference){
        return ResponseEntity.ok(teacherService.getTeachersByInstrumentAndPreference(instrument, preference));
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
    public ResponseEntity<Object> updateTeacher(@PathVariable Long id,
                                                @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Object> partialUpdateTeacher(@PathVariable Long id,
                                                       @RequestBody Teacher teacher) {
        teacherService.partialUpdateTeacher(id, teacher);
        return ResponseEntity.noContent().build();
    }

    // Endpoints for lessons

    @GetMapping(path = "/{id}/lesson")
    public ResponseEntity<Object> getLessonByStudent(@PathVariable(name = "id") long teacherId,
                                                     @RequestParam(name = "student_id") long studentId) {
        return ResponseEntity.ok(lessonService.getLessonById(teacherId, studentId));
    }

    @GetMapping(path = "/{id}/lessons")
    public ResponseEntity<Object> getActiveLessons(@PathVariable(name = "id") long teacherId) {
        return ResponseEntity.ok(teacherService.getLessons(teacherId));
    }

    @GetMapping(path = "/{id}/applications")
    public ResponseEntity<Object> getApplications(@PathVariable(name = "id") long teacherId) {
        return ResponseEntity.ok(teacherService.getApplications(teacherId));
    }

    @DeleteMapping(path = "/{id}/unsubscribe")
    public ResponseEntity<Object> unsubscribeLesson(@PathVariable("id") Long teacherId,
                                                    @RequestParam("student_id") Long studentId) {
        lessonService.deleteLesson(studentId, teacherId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{id}/update_homework")
    public ResponseEntity<Object> updateHomework(@PathVariable(name = "id") long teacherId,
                                                 @RequestParam(name = "student_id") long studentId,
                                                 @RequestBody Lesson lesson) {
        lessonService.updateHomework(studentId, teacherId, lesson);
        return ResponseEntity.noContent().build();
    }

    // Link to User

    @PatchMapping(path="/linkuser/{username}")
    public ResponseEntity<Object> addToUser(@PathVariable(name ="username") String username,
                                            @RequestParam(name = "email") String email) {
        teacherService.linkToCurrentUser(username, email);
        return ResponseEntity.noContent().build();
    }

}
