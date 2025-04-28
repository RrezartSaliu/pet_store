package com.example.pet_store.controller.rest;

import com.example.pet_store.model.Pet;
import com.example.pet_store.model.PetType;
import com.example.pet_store.service.PetService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/list-pets")
    public ResponseEntity<List<Pet>> listPets(){
        return ResponseEntity.ok(petService.pets());
    }

    @PostMapping("/create-pets")
    public ResponseEntity<List<Pet>> createPets(@RequestParam int petCount){
        if(petCount>20)
            return ResponseEntity.ok(new ArrayList<>());

        List<Pet> resultPets = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("static/petnames.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            List<String[]> readDataset = reader.readAll().stream().skip(1).toList();
            int [] randomPicks = new Random().ints(petCount, 0,1500).toArray();

            for(int randomPick : randomPicks){
                String []row = readDataset.get(randomPick);
                resultPets.add(petService.create(row[1], PetType.valueOf(row[0].toUpperCase())));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(resultPets);
    }
}
