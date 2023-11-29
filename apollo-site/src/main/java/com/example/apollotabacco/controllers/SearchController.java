package com.example.apollotabacco.controllers;


import com.example.apollotabacco.dto.UserDTO;
import com.example.apollotabacco.entities.AnimalRelationship;
import com.example.apollotabacco.entities.Bucket;
import com.example.apollotabacco.entities.Product;
import com.example.apollotabacco.entities.User;
import com.example.apollotabacco.services.AnimalRelationshipService;
import com.example.apollotabacco.services.BucketService;
import com.example.apollotabacco.services.ProductService;
import com.example.apollotabacco.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/")
@CrossOrigin("http://localhost:3000/")
public class SearchController {
    @Autowired
    private BucketService bucketService;

    @Autowired
    private UserService userService;
    @Autowired
    private AnimalRelationshipService animalRelationshipService;
    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public List<User> allProducts() {
        return userService.getAll();
    }

    @PatchMapping("update")
    public String update(@RequestBody List<User> users) {
        userService.saveList(users);
        return "Success";
    }

    @PatchMapping("deleteFromPrison")
    public String deleteFromPrison(@RequestBody User user) {
        System.out.println(user);
        user.setIsDeleted("true");
        userService.save(user);
        return "redirect:/";
    }

    @PatchMapping("deleteFromList")
    public String deleteFromList(@RequestBody User user) {
        System.out.println(user);
        user.setIsDeleted("q");
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("size")
    public Bucket getSize() {
        return bucketService.getSize(1L);
    }

    @PatchMapping("sizeUpdate")
    public String updateSize(@RequestBody int size) {
        Bucket bucket = bucketService.getSize(1L);
        bucket.setSize(size);
        bucketService.save(bucket);
        return "Success";
    }

    @PostMapping("addAnimal")
    public String addAnimal(@RequestBody User user) {
        user.setId(-1L);
        System.out.println(user);
        userService.save(user);
        return "Success";
    }
    @PatchMapping("count")
    public String countAnimal(@RequestBody List<AnimalRelationship> relationship) {
        animalRelationshipService.save(relationship);
        return "Success";
    }
    @PatchMapping("generate")
    public String generateAnimal() {
        animalRelationshipService.generateAnimals();
        return "Success";
    }
}
