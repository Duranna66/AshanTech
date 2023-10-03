package com.example.apollotabacco.controllers;

import com.example.apollotabacco.dto.ProductDTO;
import com.example.apollotabacco.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/")
public class SearchController {
    @Autowired
    private ProductService productService;
    @GetMapping("name")
    public List<ProductDTO> productView() {
        return productService.getAllProducts();
    }

}
