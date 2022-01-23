package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.dto.request.StudentRequestDto;
import nl.robbertij.matchnmusic.exception.BadRequestException;
import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student> getStudents(String instrument, String name, String residence, String preference){

        if (!instrument.isEmpty()) {
            return studentRepository.findAllByInstrument(instrument);
        }
        if (!name.isEmpty()) {
            return studentRepository.findAllByNameIgnoreCase(name);
        }
        if (!residence.isEmpty()) {
            return studentRepository.findAllByResidence(residence);
        }
        if (!preference.isEmpty()) {
            return studentRepository.findAllByPreferenceForLessonType(preference);
        }
        else {
            return studentRepository.findAll();
        }
    }

    public Student getStudent(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void deleteStudent(Long id){
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public Long addStudent(StudentRequestDto studentRequestDto){
        String email = studentRequestDto.getEmail();
        List<Student> students = (List<Student>)studentRepository.findAllByEmail(email);
        if(students.size() > 0) {
            throw new BadRequestException("Email is already taken");
        }

        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setResidence(studentRequestDto.getResidence());
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setInstrument(studentRequestDto.getInstrument());
        student.setRequest(studentRequestDto.getRequest());
        student.setPreferenceForLessonType(studentRequestDto.getPreferenceForLessonType());

        Student newStudent = studentRepository.save(student);
        return newStudent.getId();
    }

    public void updateStudent(Long id, Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isPresent()) {
            Student storedStudent = optionalStudent.get();

            student.setId(storedStudent.getId());
            studentRepository.save(student);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void partialUpdateStudent(Long id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isPresent()) {
            Student storedStudent = studentRepository.findById(id).orElse(null);

            if (student.getName() != null && !student.getName().isEmpty()){
                storedStudent.setName(student.getName());
            }
            if (student.getEmail() != null && !student.getEmail().isEmpty()){
                storedStudent.setEmail(student.getEmail());
            }
            if (student.getResidence() != null && !student.getResidence().isEmpty()){
                storedStudent.setResidence(student.getResidence());
            }
            if (student.getPhoneNumber() != null && !student.getPhoneNumber().isEmpty()) {
                storedStudent.setPhoneNumber(student.getPhoneNumber());
            }
            if (student.getInstrument() != null && !student.getInstrument().isEmpty()){
                storedStudent.setInstrument(student.getInstrument());
            }
            if (student.getRequest() != null && !student.getRequest().isEmpty()){
                storedStudent.setRequest(student.getRequest());
            }
            if (student.getPreferenceForLessonType() != null && !student.getPreferenceForLessonType().isEmpty()){
                storedStudent.setPreferenceForLessonType(student.getPreferenceForLessonType());
            }
            studentRepository.save(storedStudent);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }
}
