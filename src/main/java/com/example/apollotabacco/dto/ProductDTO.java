package com.example.apollotabacco.dto;

import javax.swing.*;

public record ProductDTO (
         Long id,
         ImageIcon photo,
         int price,
         String manufacturer,
         String  description,
         String type,
         long likes
){
}
