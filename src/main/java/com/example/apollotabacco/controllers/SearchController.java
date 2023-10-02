package com.example.apollotabacco.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
//    @PostMapping("/{id}")
//    public void getProduct(@RequestParam("id") long id) {
//
//
//    }
    @GetMapping("/{name}")
    public void productView(@RequestParam("name") String text) {
        System.out.println(text);
    }

}
