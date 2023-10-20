package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Entity
@Table(name = "role")
@Data
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UserRole() {

    }

    @Override
    public String getAuthority() {
        return "yes";
    }
    private String name;

    public UserRole(String name) {
        this.name = name;
    }
}
