package com.example.apollotabacco.repositories;


import com.example.apollotabacco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);

    @Override
    <S extends User> S save(S entity);

    List<User> getAllBy();
    Optional<User> findById(Long id);

}
