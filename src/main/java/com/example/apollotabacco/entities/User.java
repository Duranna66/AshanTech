package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Name;
    private UserRoles role;
    @Email(message = "wrong email")
    private String email;
    private String password;
    private String phoneNumber;
//    @OneToOne(mappedBy = "user")
//    private Bucket bucket;
}
