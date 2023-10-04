package com.example.apollotabacco.controllers;

import com.example.apollotabacco.dto.ProductDTO;
import com.example.apollotabacco.entities.Product;
import com.example.apollotabacco.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gridProduct")
public class GridProductController {
    @Autowired
    private ProductRepo productRepo;
    @PostMapping("")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        try {
            productRepo.save(product);
        }
        catch (Exception e) {
                new ResponseEntity<>(product, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @DeleteMapping("")
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product) {
        productRepo.delete(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
     }
}
