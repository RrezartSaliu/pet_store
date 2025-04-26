package com.example.pet_store.service;

import com.example.pet_store.model.BuyHistory;
import com.example.pet_store.repository.BuyHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BuyHistoryServiceImpl implements BuyHistoryService{
    private final BuyHistoryRepository buyHistoryRepository;

    public BuyHistoryServiceImpl(BuyHistoryRepository buyHistoryRepository) {
        this.buyHistoryRepository = buyHistoryRepository;
    }

    @Override
    public BuyHistory create(Integer successfulBuyCount, Integer unsuccessfulBuyCount) {
        return buyHistoryRepository.save(new BuyHistory(LocalDate.now(), successfulBuyCount, unsuccessfulBuyCount));
    }

    @Override
    public List<BuyHistory> historyLogs() {
        return buyHistoryRepository.findAll();
    }
}
