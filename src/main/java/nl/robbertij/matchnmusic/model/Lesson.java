package nl.robbertij.matchnmusic.model;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    // attributes

    @EmbeddedId
    private StudentTeacherKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(length = 4000)
    private String homework;

    public Lesson() {
    }

    // constructors

    public Lesson(StudentTeacherKey id, Student student, Teacher teacher, String homework) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.homework = homework;
    }

    // getters and setters


    public StudentTeacherKey getId() {
        return id;
    }

    public void setId(StudentTeacherKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }
}
