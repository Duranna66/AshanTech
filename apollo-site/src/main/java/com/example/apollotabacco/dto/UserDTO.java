package com.example.apollotabacco.dto;

import com.example.apollotabacco.entities.Bucket;
import com.example.apollotabacco.entities.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;

import java.util.List;

public record UserDTO (
         Long id,
         String name,
         List<UserRole> roles,
         String email,
         String password,
         String phoneNumber,
         Bucket bucket
){
}
