package com.example.pet_store.service;

import com.example.pet_store.model.Pet;
import com.example.pet_store.model.PetType;

import java.util.List;

public interface PetService {
    List<Pet> pets();
    Pet create(String name, PetType petType);
    List<Pet> setOwnersForPets(List<Pet> pets);
}
