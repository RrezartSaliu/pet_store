package com.example.pet_store.repository;

import com.example.pet_store.model.BuyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyHistoryRepository extends JpaRepository<BuyHistory, Long> {
}
