package com.example.apollotabacco.controllers;


import com.example.apollotabacco.entities.Bucket;
import com.example.apollotabacco.entities.Product;
import com.example.apollotabacco.entities.User;
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
    private UserService userService;

    @GetMapping("all")
    public List<User> allProducts() {
        return userService.getAll();
    }
    @PatchMapping("test")
    public String test(@RequestBody List<User> users) {

//        users.stream().filter(x -> x.getIsDeleted().equals("false")).forEach(x -> System.out.println(x.getName()));
        userService.save(users);
        return "Succ";
    }


}
