package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.dto.request.TeacherRequestDto;
import nl.robbertij.matchnmusic.model.Teacher;
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
}
