package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "isDeleted")
    private String isDeleted;
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRole> roles;

    private String email;
    private String password;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @OneToOne(mappedBy = "user")
    private Bucket bucket;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
