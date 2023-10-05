package com.example.apollotabacco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
public class Like {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_product",referencedColumnName="id",nullable=false,unique=true)
    private Product product;
}
