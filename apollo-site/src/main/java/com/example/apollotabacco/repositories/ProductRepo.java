package com.example.apollotabacco.repositories;

import com.example.apollotabacco.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
        @Override
        <S extends Product> S save(S entity);

}
