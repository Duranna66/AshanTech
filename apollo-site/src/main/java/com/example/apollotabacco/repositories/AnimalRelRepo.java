package com.example.apollotabacco.repositories;

import com.example.apollotabacco.entities.AnimalRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRelRepo extends JpaRepository<AnimalRelationship, Long> {
    @Override
    <S extends AnimalRelationship> S save(S entity);

    @Override
    default <S extends AnimalRelationship> List<S> saveAll(Iterable<S> entities) {
        return null;
    }
//    @Query("SELECT ar FROM AnimalRelationship ar WHERE ar.animalId1 = :id1 AND ar.animalId2 = :id2")
    AnimalRelationship findByAnimalId1AndAnimalId2(Long id1, Long id2);

}
