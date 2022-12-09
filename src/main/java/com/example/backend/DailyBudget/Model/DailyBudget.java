package com.example.backend.DailyBudget.Model;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import com.example.backend.Meal.Model.Meal;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DailyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dailyBudget_Id;
    private float money;
    private LocalDate date;
    @OneToMany
    private Set<AdditionalExpenses> additionalExpenses;
    @OneToMany
    private Set<Meal> meal;

       public DailyBudget(float money, LocalDate date, Set<AdditionalExpenses> additionalExpenses,
                          Set<Meal> meal){
        this.money = money;
        this.date = date;
        this.additionalExpenses = additionalExpenses;
        this.meal = meal;
    }

    @Override //2811 2022 hans: givet dailybudget en tostring for debugging
    public String toString(){
           return "This is dailyBudget object toString. STATUS: " +
                   "ID: " + this.dailyBudget_Id +
                   ", AND money: " + this.money +
                   ", \nAND date: " + this.date +
                   ", AND set of expenses: " + this.additionalExpenses +
                   ", AND set of meals: " + this.meal +
                   ". \nEnd of status of DailyBudget object. \n";
    }

}