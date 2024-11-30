package com.project.School.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public class ResponseDto<T> {

    private T data;
    private int status;
    private String message;
    private Instant timeStamp;

    public ResponseDto(T data, int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.timeStamp = Instant.now();
    }

    public ResponseDto(T data, int status, String message, Instant timeStamp) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public ResponseDto() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}
