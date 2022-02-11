package nl.robbertij.matchnmusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String age;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String residence;
    private String instrument;

    @Column(length = 2000)
    private String request;

    @Column(name = "preference_for_lesson_type")
    private String preferenceForLessonType;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Lesson> lesson;

    public Student() {};

    public Student(Long id,
                   String name,
                   String email,
                   String age,
                   String phoneNumber,
                   String residence,
                   String instrument,
                   String request,
                   String preferenceForLessonType,
                   List<Lesson> lesson) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.instrument = instrument;
        this.request = request;
        this.preferenceForLessonType = preferenceForLessonType;
        this.lesson = lesson;
    }

    public Student(String name,
                   String email,
                   String age,
                   String phoneNumber,
                   String residence,
                   String instrument,
                   String request,
                   String preferenceForLessonType,
                   List<Lesson> lesson) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.instrument = instrument;
        this.request = request;
        this.preferenceForLessonType = preferenceForLessonType;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidence() { return residence; }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getPreferenceForLessonType() {
        return preferenceForLessonType;
    }

    public void setPreferenceForLessonType(String preferenceForLessonType) {
        this.preferenceForLessonType = preferenceForLessonType;
    }

    public List<Lesson> getLessons() {
        return lesson;
    }

    public void removeLessons(Lesson homework) {
        this.lesson.remove(homework);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", residence='" + residence + '\'' +
                ", instrument='" + instrument + '\'' +
                ", request='" + request + '\'' +
                ", preferenceForLessonType='" + preferenceForLessonType + '\'' +
                ", lessons=" + lesson +
                '}';
    }
}
