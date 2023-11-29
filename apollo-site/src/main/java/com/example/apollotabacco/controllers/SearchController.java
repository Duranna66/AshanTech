package com.example.apollotabacco.controllers;


import com.example.apollotabacco.entities.Animal;
import com.example.apollotabacco.entities.AnimalRelationship;
import com.example.apollotabacco.entities.Size;
import com.example.apollotabacco.services.AnimalRelationshipService;
import com.example.apollotabacco.services.SizeService;
import com.example.apollotabacco.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/")
@CrossOrigin("http://localhost:3000/")
public class SearchController {
    @Autowired
    private SizeService sizeService;

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalRelationshipService animalRelationshipService;

    @GetMapping("all")
    public List<Animal> allProducts() {
        return animalService.getAll();
    }

    @PatchMapping("update")
    public String update(@RequestBody List<Animal> animals) {
        System.out.println(animals);
        animalService.saveList(animals);
        return "Success";
    }

    @PatchMapping("deleteFromPrison")
    public String deleteFromPrison(@RequestBody Animal animal) {
        System.out.println(animal);
        animal.setIsDeleted("true");
        animalService.save(animal);
        return "redirect:/";
    }

    @PatchMapping("deleteFromList")
    public String deleteFromList(@RequestBody Animal animal) {
        System.out.println(animal);
        animal.setIsDeleted("q");
        animalService.save(animal);
        return "redirect:/";
    }

    @GetMapping("size")
    public Size getSize() {
        return sizeService.getSize(1L);
    }

    @PatchMapping("sizeUpdate")
    public String updateSize(@RequestBody int size) {
        Size size1 = sizeService.getSize(1L);
        if(size1 == null) {
            size1 = new Size();
        }
        size1.setSize(size);
        System.out.println(size1);
        sizeService.save(size1);
        return "Success";
    }

    @PostMapping("addAnimal")
    public String addAnimal(@RequestBody Animal animal) {
        animal.setId(-1L);
        animalService.save(animal);
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
