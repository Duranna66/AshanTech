package com.example.apollotabacco.repositories;

import com.example.apollotabacco.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
