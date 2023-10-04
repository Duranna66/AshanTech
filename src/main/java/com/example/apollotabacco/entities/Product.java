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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    private ImageIcon photo;
    private int price;
    private String manufacturer;
    private String  description;
    private String type;
//    @OneToMany(mappedBy = "")
//    private List<Like> likes;
//    @OneToMany
//    private List<Tag> tags;

}
