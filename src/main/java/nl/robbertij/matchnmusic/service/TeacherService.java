package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.dto.request.TeacherRequestDto;
import nl.robbertij.matchnmusic.exception.BadRequestException;
import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.StudentTeacherKey;
import nl.robbertij.matchnmusic.model.Teacher;
import nl.robbertij.matchnmusic.repository.LessonRepository;
import nl.robbertij.matchnmusic.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Iterable<Teacher> getTeachers(String instrument, String preference){
        if(!instrument.isEmpty()){
            return teacherRepository.findAllByInstrumentsContainingIgnoreCase(instrument);
        }
        if(!preference.isEmpty()){
            return teacherRepository.findAllByPreferenceForLessonType(preference);
        }
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isPresent()){
            return optionalTeacher.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void deleteTeacher(Long id) {
        if(teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public Long addTeacher(TeacherRequestDto teacherRequestDto) {
        String email = teacherRequestDto.getEmail();
        List<Teacher> teachers = (List<Teacher>)teacherRepository.findAllByEmail(email);
        if(teachers.size() > 0) {
            throw new BadRequestException("Email is already taken");
        }

        Teacher teacher = new Teacher();
        teacher.setName(teacherRequestDto.getName());
        teacher.setEmail(teacherRequestDto.getEmail());
        teacher.setAge(teacherRequestDto.getAge());
        teacher.setPhoneNumber(teacherRequestDto.getPhoneNumber());
        teacher.setResidence(teacherRequestDto.getResidence());
        teacher.setInstruments(teacherRequestDto.getInstruments());
        teacher.setDescription(teacherRequestDto.getDescription());
        teacher.setExperience(teacherRequestDto.getExperience());
        teacher.setPreferenceForLessonType(teacherRequestDto.getPreferenceForLessonType());

        Teacher newTeacher = teacherRepository.save(teacher);
        return newTeacher.getId();
    }

    public void updateTeacher(Long id, Teacher teacher){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if(optionalTeacher.isPresent()){
            Teacher storedTeacher = optionalTeacher.get();

            teacher.setId(storedTeacher.getId());
            teacherRepository.save(teacher);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void partialUpdateTeacher(Long id, Teacher teacher){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if(optionalTeacher.isPresent()){
            Teacher storedTeacher = teacherRepository.findById(id).orElse(null);

            if (teacher.getName() != null && !teacher.getName().isEmpty()){
                storedTeacher.setName(teacher.getName());
            }
            if (teacher.getEmail() != null && !teacher.getEmail().isEmpty()){
                storedTeacher.setEmail(teacher.getEmail());
            }
            if (teacher.getResidence() != null && !teacher.getResidence().isEmpty()){
                storedTeacher.setResidence(teacher.getResidence());
            }
            if (teacher.getPhoneNumber() != null && !teacher.getPhoneNumber().isEmpty()){
                storedTeacher.setPhoneNumber(teacher.getPhoneNumber());
            }
            if (teacher.getInstruments() != null && !teacher.getInstruments().isEmpty()){
                storedTeacher.setInstruments(teacher.getInstruments());
            }
            if (teacher.getDescription() != null && teacher.getDescription().isEmpty()){
                storedTeacher.setDescription(teacher.getDescription());
            }
            if (teacher.getExperience() != null && !teacher.getExperience().isEmpty()){
                storedTeacher.setExperience(teacher.getExperience());
            }
            if (teacher.getPreferenceForLessonType() != null && !teacher.getPreferenceForLessonType().isEmpty()){
                storedTeacher.setPreferenceForLessonType(teacher.getPreferenceForLessonType());
            }
            teacherRepository.save(storedTeacher);
        }
        else {
            throw new BadRequestException("ID does not exist");
        }
    }

    public List<Lesson> getLessons(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            return teacher.getLessons();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }
}
