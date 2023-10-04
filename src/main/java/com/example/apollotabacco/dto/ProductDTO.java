package com.example.apollotabacco.dto;


import javax.swing.*;
import java.util.List;

public record ProductDTO (
         Long id,
         String name,
//         ImageIcon photo,
         int price,
         String manufacturer,
         String  description,
         String type
//         List<Like> likes
){
}
