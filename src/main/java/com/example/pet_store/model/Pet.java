package com.example.pet_store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private AppUser owner;
    private String name;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private String description;
    private LocalDate birthDate;
    private BigDecimal price;
    private Integer rating;

    public void setRating(Integer rating) {
        if(type.equals(PetType.DOG))
        this.rating = rating;
    }
}
