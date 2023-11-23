package com.example.apollotabacco.repositories;

import com.example.apollotabacco.entities.Bucket;
import com.example.apollotabacco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<Bucket, Long> {
    Bucket getBucketById(Long id);

    @Override
    <S extends Bucket> S save(S entity);
}
