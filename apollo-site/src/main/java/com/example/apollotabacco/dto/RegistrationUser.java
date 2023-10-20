package com.example.apollotabacco.dto;

import lombok.Data;

@Data
public class RegistrationUser {
    private String login;
    private String password;
    private String confirmPassword;

    public RegistrationUser(String login, String password, String confirmPassword) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
