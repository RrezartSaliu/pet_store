package com.example.pet_store.service;

import com.example.pet_store.model.BuyHistory;
import java.util.List;

public interface BuyHistoryService {
    BuyHistory create(Integer successfulBuyCount, Integer unsuccessfulBuyCount);
    List<BuyHistory> historyLogs();
}
