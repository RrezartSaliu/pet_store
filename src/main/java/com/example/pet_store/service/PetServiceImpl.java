package com.example.pet_store.service;

import com.example.pet_store.model.Pet;
import com.example.pet_store.model.PetType;
import com.example.pet_store.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> pets() {
        return petRepository.findAll();
    }

    @Override
    public Pet create(String name, PetType petType) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(petType);

        LocalDate birthDate = birthDate();

        pet.setBirthDate(birthDate);

        int years = Period.between(birthDate, LocalDate.now()).getYears();

        if(petType.equals(PetType.DOG)){
            int rating = new Random().nextInt(11);
            pet.setRating(rating);
            pet.setPrice(BigDecimal.valueOf((1.0*years) + (1.0*rating)));
            pet.setDescription("Dog named "+name+"! Price: "+ pet.getPrice());
        }
        else {
            pet.setPrice(BigDecimal.valueOf(1.0*years));
            pet.setDescription("Cat named "+ name + "! Price: "+ pet.getPrice());
        }
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> setOwnersForPets(List<Pet> pets) {
        return petRepository.saveAll(pets);
    }

    private LocalDate birthDate(){
        LocalDate startDate = LocalDate.of(2010,1,1);
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.now();
        long end = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay);
    }
}
