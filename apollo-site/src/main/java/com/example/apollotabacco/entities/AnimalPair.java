package com.example.apollotabacco.entities;

import java.util.Objects;

public class AnimalPair {
    private Long animalId1;
    private Long animalId2;

    public AnimalPair(Long animalId1, Long animalId2) {
        this.animalId1 = animalId1;
        this.animalId2 = animalId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalPair that = (AnimalPair) o;
        return (Objects.equals(animalId1, that.animalId1) && Objects.equals(animalId2, that.animalId2)) ||
                (Objects.equals(animalId1, that.animalId2) && Objects.equals(animalId2, that.animalId1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId1, animalId2) + Objects.hash(animalId2, animalId1);
    }
}
