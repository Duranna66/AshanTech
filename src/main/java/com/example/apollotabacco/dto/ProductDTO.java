package com.example.apollotabacco.dto;




import com.example.apollotabacco.entities.Like;
import com.example.apollotabacco.entities.Tag;

import javax.swing.*;
import java.util.List;

public record ProductDTO (
         Long id,
         String name,
//         ImageIcon photo,
         double price,
         String manufacturer,
         String  description,
         String type,
         List<Like> likes,
         List<Tag> tags

){
}
