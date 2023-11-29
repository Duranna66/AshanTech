package com.example.apollotabacco.dto;

public record  UserDTO(
        String name,
        String isPredator,
        String isDeleted,
        Integer phoneNumber
) {
}
