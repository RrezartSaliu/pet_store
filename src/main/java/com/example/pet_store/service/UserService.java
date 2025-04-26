package com.example.pet_store.service;

import com.example.pet_store.model.AppUser;

import java.util.List;

public interface UserService {
    List<AppUser> users();
    AppUser create(String firstName, String lastName);
    void buyPet();
}
