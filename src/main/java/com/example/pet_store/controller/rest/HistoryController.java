package com.example.pet_store.controller.rest;

import com.example.pet_store.model.BuyHistory;
import com.example.pet_store.service.BuyHistoryService;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/history")
public class HistoryController {
    private final BuyHistoryService buyHistoryService;

    public HistoryController(BuyHistoryService buyHistoryService) {
        this.buyHistoryService = buyHistoryService;
    }

    @GetMapping("/history-log")
    public ResponseEntity<List<BuyHistory>> historyLog(){
        return ResponseEntity.ok(buyHistoryService.historyLogs());
    }
}
