package com.project.School.dto;

public class CourseDto {

    private Long id;
    private String name;

    public CourseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseDto() {
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
}
