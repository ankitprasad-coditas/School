package com.project.School.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public class AuthRequestDto {

    @Email(message = "Enter Valid Email ID")
    private String username;
    
    private String password;

    public AuthRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}