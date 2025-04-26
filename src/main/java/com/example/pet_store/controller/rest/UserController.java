package com.example.pet_store.controller.rest;

import com.example.pet_store.model.AppUser;
import com.example.pet_store.service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-users")
    private ResponseEntity<List<AppUser>> listUsers (){
        return ResponseEntity.ok(userService.users());
    }

    @PostMapping("/create-users")
    private ResponseEntity<List<AppUser>> createUsers (@RequestParam int userCount) throws IOException {
        if (userCount >10)
            return ResponseEntity.ok(new ArrayList<>());

        ClassPathResource resource = new ClassPathResource("static/" + "names_dataset.csv");
        List<String> namesDataset = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))){
            reader.readAll().stream().skip(1).forEach(row -> {
                namesDataset.add(row[0]+" "+row[1]);
            });
        } catch (CsvException e) {
            e.printStackTrace();
        }
        int [] randomPicks = new Random().ints(userCount, 0,1000).toArray();
        List<AppUser> resultUsers = new ArrayList<>();

        for (int randomPick : randomPicks){
            String [] fullname = namesDataset.get(randomPick).split(" ");
            resultUsers.add(userService.create(fullname[0], fullname[1]));
        }
        return ResponseEntity.ok(resultUsers);
    }

    @PostMapping("/buy")
    public ResponseEntity<List<AppUser>> buy(){
        userService.buyPet();
        return ResponseEntity.ok(userService.users());
    }
}
