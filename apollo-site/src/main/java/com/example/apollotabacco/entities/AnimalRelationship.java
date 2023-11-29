package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_relationship_seq")
    @SequenceGenerator(name = "animal_relationship_seq", sequenceName = "animal_relationship_seq", initialValue = 1, allocationSize = 1)
    private int count;
    private Long animalId1;
    private Long animalId2;

}
