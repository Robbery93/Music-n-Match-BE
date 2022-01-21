package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.dto.StudentRequestDto;
import nl.robbertij.matchnmusic.model.Student;
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

    @Autowired
    private StudentService studentService;

    // Voeg parameters toe
    @GetMapping
    public ResponseEntity<Object> getStudents(@RequestParam(name = "instrument", defaultValue = "") String instrument,
                                              @RequestParam(name = "residence", defaultValue = "") String residence,
                                              @RequestParam(name = "pref", defaultValue = "") String preference) {
        return ResponseEntity.ok(studentService.getStudents(instrument, residence, preference));
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
    public ResponseEntity<Object> partialUpdateBook(@PathVariable Long id, @RequestBody Student student) {
        studentService.partialUpdateStudent(id, student);

        return ResponseEntity.noContent().build();
    }
}
