package com.example.apollotabacco.repositories;

import com.example.apollotabacco.dto.ProductDTO;
import com.example.apollotabacco.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
        List<Product> findAll();
        List<Product> findByName(String name);

        @Override
        <S extends Product> S save(S entity);

        @Override
        void delete(Product entity);
}
