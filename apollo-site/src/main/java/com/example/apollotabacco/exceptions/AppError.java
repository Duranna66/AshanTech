package com.example.apollotabacco.exceptions;

import lombok.Data;

import java.time.Duration;
import java.util.Date;
@Data
public class AppError {
    private int status;
    private String message;
    private Date time;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.time = new Date();
    }
}
