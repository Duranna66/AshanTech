package com.example.apollotabacco.repositories;


import com.example.apollotabacco.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepo extends JpaRepository<Animal, Long> {
    Animal findByName(String name);

    @Override
    <S extends Animal> S save(S entity);

    List<Animal> getAllBy();
    Optional<Animal> findById(Long id);

}
