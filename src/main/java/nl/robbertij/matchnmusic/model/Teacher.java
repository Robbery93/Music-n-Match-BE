package nl.robbertij.matchnmusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

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
    private String instruments;

    // to store a large String
    @Column(length = 2000)
    private String description;

    // to store a large String
    @Column(length = 2000)
    private String experience;

    @Column(name = "preference_for_lesson_type")
    private String preferenceForLessonType;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public Teacher() {};

    public Teacher(Long id,
                   String name,
                   String email,
                   String age,
                   String phoneNumber,
                   String residence,
                   String instruments,
                   String description,
                   String experience,
                   String preferenceForLessonType,
                   List<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.instruments = instruments;
        this.description = description;
        this.experience = experience;
        this.preferenceForLessonType = preferenceForLessonType;
        this.lessons = lessons;
    }

    public Teacher(String name,
                   String email,
                   String age,
                   String phoneNumber,
                   String residence,
                   String instruments,
                   String description,
                   String experience,
                   String preferenceForLessonType,
                   List<Lesson> lessons) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.instruments = instruments;
        this.description = description;
        this.experience = experience;
        this.preferenceForLessonType = preferenceForLessonType;
        this.lessons = lessons;
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

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getInstruments() {
        return instruments;
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPreferenceForLessonType() {
        return preferenceForLessonType;
    }

    public void setPreferenceForLessonType(String preferenceForLessonType) {
        this.preferenceForLessonType = preferenceForLessonType;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Lesson getLesson(StudentTeacherKey id, List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getId().equals(id)){
                return lesson;
            }
        }
        return null;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLessons(Lesson homework) {
        this.lessons.add(homework);
    }

    public void removeLessons(Lesson homework) {
        this.lessons.remove(homework);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", residence='" + residence + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", instruments='" + instruments + '\'' +
                ", description='" + description + '\'' +
                ", experience='" + experience + '\'' +
                ", preferenceForLessonType='" + preferenceForLessonType + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
