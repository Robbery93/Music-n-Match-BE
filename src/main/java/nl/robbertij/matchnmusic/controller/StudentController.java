package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.dto.request.StudentRequestDto;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.service.LessonService;
import nl.robbertij.matchnmusic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;
    private final LessonService lessonService;

    public StudentController(StudentService studentService,
                             LessonService lessonService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    // Endpoints for students

    @GetMapping
    public ResponseEntity<Object> getStudents(@RequestParam(name = "instrument", defaultValue = "") String instrument,
                                              @RequestParam(name = "name", defaultValue = "") String name,
                                              @RequestParam(name = "pref", defaultValue = "") String preference) {
        return ResponseEntity.ok(studentService.getStudents(instrument, name, preference));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> addStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        Long newId = studentService.addStudent(studentRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Object> partialUpdateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.partialUpdateStudent(id, student);

        return ResponseEntity.noContent().build();
    }

    // Endpoints for lesson

    @GetMapping(path = "/{id}/lesson")
    public ResponseEntity<Object> getLesson(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.getLessons(id));
    }

    @DeleteMapping(path = "/{student_id}/unsubscribe/{teacher_id}")
    public ResponseEntity<Object> unsubscribe(@PathVariable("student_id") Long studentId,
                                              @PathVariable("teacher_id") Long teacherId) {
        lessonService.deleteLesson(teacherId, studentId);
        return ResponseEntity.noContent().build();
    }

    // Probeersel

}
