package com.example.apollotabacco.services;

import com.example.apollotabacco.entities.UserRole;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public UserRole getUserRole() {
        return new UserRole("ROLE_USER");
    }
}
