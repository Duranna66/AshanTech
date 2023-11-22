package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
@Entity
@Table(name = "role")
@Data
public class UserRole  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UserRole() {

    }

    private String name;

    public UserRole(String name) {
        this.name = name;
    }
}
