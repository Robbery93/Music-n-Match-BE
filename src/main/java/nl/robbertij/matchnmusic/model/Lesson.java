package nl.robbertij.matchnmusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    // attributes

    @EmbeddedId
    private StudentTeacherKey id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @Column(length = 4000)
    private String homework;

    public Lesson() {
    }

    // constructor

    public Lesson(StudentTeacherKey id, Teacher teacher, Student student, String homework) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.homework = homework;
    }

    // getters and setters


    public StudentTeacherKey getId() {
        return id;
    }

    public void setId(StudentTeacherKey id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", homework='" + homework + '\'' +
                '}';
    }
}
