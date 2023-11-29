package com.example.apollotabacco.repositories;

import com.example.apollotabacco.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepo extends JpaRepository<Size, Long> {
    Size getSizeById(Long id);

    @Override
    <S extends Size> S save(S entity);
}
