package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private String manufacturer;
    private String  description;
    private String type;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Like> likes;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Tag> tags;

}
