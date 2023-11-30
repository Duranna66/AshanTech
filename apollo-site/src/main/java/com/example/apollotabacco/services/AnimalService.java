package com.example.apollotabacco.services;


import com.example.apollotabacco.entities.Animal;
import com.example.apollotabacco.repositories.AnimalRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AnimalService {
    @Autowired
    private AnimalRepo animalRepo;

    public Animal update(Animal animal) {
        Configuration configuration = new Configuration().addAnnotatedClass(Animal.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Animal animal1 = session.get(Animal.class, animal.getId());
            animal1.setIsDeleted("false");
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return animal;
    }

    public List<Animal> getAll() {
        return animalRepo.getAllBy();
    }
    @Transactional

    public List<Animal> saveList(List<Animal> animals) {
        animalRepo.saveAll(animals);
        return animals;
    }
    public Animal save(Animal animal) {
        animalRepo.save(animal);
        return animal;
    }
    public Animal findById(Long id) {
        return animalRepo.findById(id).get();
    }
    public Animal findByName(String name) {
        return animalRepo.findByName(name);
    }
}
