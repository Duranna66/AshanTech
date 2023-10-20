package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "buckets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
