package com.example.pet_store.service;

import com.example.pet_store.model.AppUser;
import com.example.pet_store.model.Pet;
import com.example.pet_store.model.PetType;
import com.example.pet_store.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PetService petService;
    private final BuyHistoryService buyHistoryService;

    public UserServiceImpl(UserRepository userRepository, PetService petService, BuyHistoryService buyHistoryService) {
        this.userRepository = userRepository;
        this.petService = petService;
        this.buyHistoryService = buyHistoryService;
    }

    @Override
    public List<AppUser> users() {
        return userRepository.findAll();
    }

    @Override
    public AppUser create(String firstName, String lastName) {
        String [] emails = {"gmail.com", "outlook.com" , "yahoo.com"};

        AppUser user = new AppUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        BigDecimal budget = BigDecimal.valueOf(new Random().nextDouble(30.00,120.00));
        user.setBudget(budget);
        user.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase()+"@"+emails[new Random().nextInt(3)]);

        return userRepository.save(user);
    }

    @Override
    public void buyPet() {
        List<AppUser> users = this.users();
        List<Pet> pets = petService.pets();
        Integer successfulBuyCount = 0;
        Integer unsuccessfulBuyCount = 0;

        for(AppUser user: users){
            boolean userBoughtPet = false;
            for(Pet pet : pets){
                if ((pet.getOwner() == null) && (user.getBudget().compareTo(pet.getPrice()) >= 0)){
                    pet.setOwner(user);
                    user.setBudget(user.getBudget().subtract(pet.getPrice()));
                    if(pet.getType().equals(PetType.DOG)){
                        System.out.println("Woof, dog "+ pet.getName() + " has owner "+ user.getFirstName() + " " + user.getLastName());
                    }
                    else{
                        System.out.println("Meow, cat "+ pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
                    }
                    userBoughtPet = true;
                    break;
                }
            }

            if(userBoughtPet)
                successfulBuyCount++;
            else unsuccessfulBuyCount++;

        }

        buyHistoryService.create(successfulBuyCount, unsuccessfulBuyCount);
        userRepository.saveAll(users);
        petService.setOwnersForPets(pets);
    }
}
