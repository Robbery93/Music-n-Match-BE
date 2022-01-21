package nl.robbertij.matchnmusic.model;

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
    private String residence;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String instruments;

    @Lob
    private String description;

    @Lob
    private String experience;

    @Column(name = "preference_for_lesson_type")
    private String preferenceForLessonType;

    @JsonIgnoreProperties("teacher")
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    public Teacher() {};

    public Teacher(Long id,
                   String name,
                   String email,
                   String residence,
                   String phoneNumber,
                   String instruments,
                   String description,
                   String experience,
                   String preferenceForLessonType,
                   List<Student> students) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.instruments = instruments;
        this.description = description;
        this.experience = experience;
        this.preferenceForLessonType = preferenceForLessonType;
        this.students = students;
    }

    public Teacher(String name,
                   String email,
                   String residence,
                   String phoneNumber,
                   String instruments,
                   String description,
                   String experience,
                   String preferenceForLessonType,
                   List<Student> students) {
        this.name = name;
        this.email = email;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.instruments = instruments;
        this.description = description;
        this.experience = experience;
        this.preferenceForLessonType = preferenceForLessonType;
        this.students = students;
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

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
