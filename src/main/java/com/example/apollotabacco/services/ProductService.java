package com.example.apollotabacco.services;

import com.example.apollotabacco.dto.ProductDTO;
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
        public List<ProductDTO> getAllProducts() {
            return productRepo.findAll().stream().map(x-> new ProductDTO(
                    x.getId(),
                    x.getName(),
                    x.getPrice(),
                    x.getManufacturer(),
                    x.getDescription(),
                    x.getType(),
                    x.getLikes(),
                    x.getTags()
                  )).toList();
        }
        public List<ProductDTO> getProductsByName(String name) {
            return productRepo.findByName(name).stream().map(x-> new ProductDTO(
                    x.getId(),
                    x.getName(),
                    x.getPrice(),
                    x.getManufacturer(),
                    x.getDescription(),
                    x.getType(),
                    x.getLikes(),
                    x.getTags()
                   )).toList();
        }
}
