package com.example.apollotabacco.services;

import com.example.apollotabacco.entities.Product;
import com.example.apollotabacco.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
        public Product save(Product product) {
            return productRepo.save(product);
        }
}
