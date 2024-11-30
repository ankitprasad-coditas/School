package com.project.School.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public class StudentDto {

    private Long id;

    @NotBlank(message = "Name Cannot Be Empty")
    private String name;

    @Positive(message = "Age Should be positive")
    private Long age;

    @NotBlank(message = "Standard cannot be blank")
    @Positive(message = "Enter a valid Class")
    private Long standard;

    @Email(message = "Enter A Valid EmailId")
    private String emailId;

    private Set<Long> courseId;

    public StudentDto(Long id, String name, Long age, Long standard, String emailId, Set<Long> courseId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.standard = standard;
        this.emailId = emailId;
        this.courseId = courseId;
    }

    public StudentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name Cannot Be Empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name Cannot Be Empty") String name) {
        this.name = name;
    }

    public @Positive(message = "Age Should be positive") Long getAge() {
        return age;
    }

    public void setAge(@Positive(message = "Age Should be positive") Long age) {
        this.age = age;
    }

    public @NotBlank(message = "Standard cannot be blank") @Positive(message = "Enter a valid Class") Long getStandard() {
        return standard;
    }

    public void setStandard(@NotBlank(message = "Standard cannot be blank") @Positive(message = "Enter a valid Class") Long standard) {
        this.standard = standard;
    }

    public @Email(message = "Enter A Valid EmailId") String getEmailId() {
        return emailId;
    }

    public void setEmailId(@Email(message = "Enter A Valid EmailId") String emailId) {
        this.emailId = emailId;
    }

    public Set<Long> getCourseId() {
        return courseId;
    }

    public void setCourseId(Set<Long> courseId) {
        this.courseId = courseId;
    }
}
