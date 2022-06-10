package nl.robbertij.matchnmusic.service;

import nl.robbertij.matchnmusic.dto.request.StudentRequestDto;
import nl.robbertij.matchnmusic.exception.BadRequestException;
import nl.robbertij.matchnmusic.exception.RecordNotFoundException;
import nl.robbertij.matchnmusic.model.Lesson;
import nl.robbertij.matchnmusic.model.Student;
import nl.robbertij.matchnmusic.model.User;
import nl.robbertij.matchnmusic.repository.LessonRepository;
import nl.robbertij.matchnmusic.repository.StudentRepository;
import nl.robbertij.matchnmusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          LessonRepository lessonRepository,
                          UserRepository userRepository,
                          UserService userService) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Student> getStudents(String instrument, String name, String preference){

        if (!preference.isEmpty()) {
            return studentRepository.findAllByPreferenceForLessonType(preference);
        }
        if (!instrument.isEmpty()) {
            return studentRepository.findAllByInstrument(instrument);
        }
        if (!name.isEmpty()) {
            return studentRepository.findAllByNameIgnoreCase(name);
        }
        else {
            return studentRepository.findAll();
        }
    }

    public List<Student> getStudentsByInstrumentAndPreference(String instrument, String preference){
        return studentRepository.findAllByInstrumentAndPreferenceForLessonType(instrument, preference);
    }

    public Student getStudentById(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void deleteStudentById(Long id){
        if (studentRepository.existsById(id)) {

            List<Lesson> allLessonsOfStudent = lessonRepository.findAllByStudentId(id);
                lessonRepository.deleteAll(allLessonsOfStudent);

            studentRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public Long addStudent(StudentRequestDto studentRequestDto){
        String email = studentRequestDto.getEmail();
        List<Student> students = studentRepository.findAllByEmail(email);
        if(students.size() > 0) {
            throw new BadRequestException("Email is already taken");
        }

        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setResidence(studentRequestDto.getResidence());
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
            if (student.getAge() != null && !student.getAge().isEmpty()){
                storedStudent.setAge(student.getAge());
            }
            if (student.getPhoneNumber() != null && !student.getPhoneNumber().isEmpty()) {
                storedStudent.setPhoneNumber(student.getPhoneNumber());
            }
            if (student.getResidence() != null && !student.getResidence().isEmpty()){
                storedStudent.setResidence(student.getResidence());
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

    public List<Lesson> getLesson(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return student.getLessons();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public List<Lesson> getApplications(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return student.getApplications();
        }
        else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public void linkToCurrentUser(String username, String email) {
        User currentUser = userService.getUser(username);

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            currentUser.setStudent(student);
            userRepository.save(currentUser);
        } else {
            throw new RecordNotFoundException("student niet gevonden");
        }
    }
}
