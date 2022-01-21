package nl.robbertij.matchnmusic.dto;

import javax.validation.constraints.*;

public class StudentRequestDto {

    // attributes
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String residence;

    @NotBlank
    @Digits(integer = 10, fraction = 0)
    // accepts 06******** & +31* ********)
    private String phoneNumber;

    @NotBlank
    private String instrument;

    @NotBlank
    private String request;

    @NotBlank
    private String preferenceForLessonType;

    // getters & setters
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
}
