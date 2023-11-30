package com.example.apollotabacco.services;

import com.example.apollotabacco.entities.Animal;
import com.example.apollotabacco.entities.AnimalPair;
import com.example.apollotabacco.entities.AnimalRelationship;
import com.example.apollotabacco.repositories.AnimalRelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnimalRelationshipService {
    @Autowired
    private AnimalRelRepo animalRelRepo;
    @Autowired
    private AnimalService animalService;
    public AnimalRelationship save(List <AnimalRelationship> relationship) {
        relationship.forEach(x -> {
            AnimalRelationship animalRelationship = animalRelRepo.findByAnimalId1AndAnimalId2(x.getAnimalId1(), x.getAnimalId2());
            if(animalRelationship != null) {
                animalRelationship.setCount(animalRelationship.getCount() + 1);
                animalRelRepo.save(animalRelationship);
            }
            else {
                x.setCount(1);
                animalRelRepo.save(x);
            }
        });
        return null;
    }
    public List<AnimalRelationship> saveAll(List<AnimalRelationship> list) {
        return animalRelRepo.saveAll(list);
    }
    public void generateAnimals() {
        List<AnimalRelationship> list =  animalRelRepo.findAll();
        Map<AnimalPair, Integer> connectionCount = new HashMap<>();
        for (AnimalRelationship connection : list) {
            AnimalPair pair = new AnimalPair(connection.getAnimalId1(), connection.getAnimalId2());
            connectionCount.put(pair, connectionCount.getOrDefault(pair, 0) + connection.getCount());
        }

        list.sort((c1, c2) -> connectionCount.get(new AnimalPair(c2.getAnimalId1(), c2.getAnimalId2())) -
                connectionCount.get(new AnimalPair(c1.getAnimalId1(), c1.getAnimalId2())));


        Map<Long, Long> bestPartners = new HashMap<>();
        for (AnimalRelationship connection : list) {
            Long animal1 = connection.getAnimalId1();
            Long animal2 = connection.getAnimalId2();
            if (bestPartners.containsKey(animal1) && bestPartners.get(animal1).equals(animal2) &&
                    bestPartners.containsKey(animal2) && bestPartners.get(animal2).equals(animal1)) {
                continue;
            }
            if (!bestPartners.containsKey(animal1)) {
                bestPartners.put(animal1, animal2);
            }
        }
        System.out.println(bestPartners);
        int i = 1;
        for (Long animal : bestPartners.keySet()) {
            Long partner = bestPartners.get(animal);
            Animal animal1 = animalService.findById(animal);
            Animal animal2 = animalService.findById(partner);
            animal1.setPrisonId(i);
            animal2.setPrisonId(i);
            i++;
            animalService.save(animal1);
            animalService.save(animal2);
        }

    }
}
