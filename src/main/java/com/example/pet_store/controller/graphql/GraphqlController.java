package com.example.pet_store.controller.graphql;

import com.example.pet_store.model.AppUser;
import com.example.pet_store.model.BuyHistory;
import com.example.pet_store.model.Pet;
import com.example.pet_store.service.BuyHistoryService;
import com.example.pet_store.service.PetService;
import com.example.pet_store.service.UserService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class GraphqlController {
    private final UserService userService;
    private final PetService petService;
    private final BuyHistoryService buyHistoryService;

    public GraphqlController(UserService userService, PetService petService, BuyHistoryService buyHistoryService) {
        this.userService = userService;
        this.petService = petService;
        this.buyHistoryService = buyHistoryService;
    }

    @QueryMapping
    public List<AppUser> users(){
        return userService.users();
    }

    @QueryMapping
    public List<Pet> pets(){
        return petService.pets();
    }

    @QueryMapping
    public List<BuyHistory> history_logs(){
        return buyHistoryService.historyLogs();
    }
}
