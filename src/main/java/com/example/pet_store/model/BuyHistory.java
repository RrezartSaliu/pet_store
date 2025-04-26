package com.example.pet_store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate executionDate;
    private Integer successfulBuyCount;
    private Integer unsuccessfulBuyCount;

    public BuyHistory(LocalDate executionDate, Integer successfulBuyCount, Integer unsuccessfulBuyCount) {
        this.executionDate = executionDate;
        this.successfulBuyCount = successfulBuyCount;
        this.unsuccessfulBuyCount = unsuccessfulBuyCount;
    }
}
