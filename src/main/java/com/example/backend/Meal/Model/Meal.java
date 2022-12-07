package com.example.backend.Meal.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private float percentageOfBudget;

    public Meal(String name, float percentageOfBudget){
        this.name = name;
        this.percentageOfBudget = percentageOfBudget;
    }
}
