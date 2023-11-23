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


}
