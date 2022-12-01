package com.example.backend.DailyBudget.Model;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
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

       public DailyBudget(float money, LocalDate date, Set<AdditionalExpenses> additionalExpenses){
        this.money = money;
        this.date = date;
        this.additionalExpenses = additionalExpenses;

    }

    @Override //2811 2022 hans: givet dailybudget en tostring for debugging
    public String toString(){
           return "This is dailyBudget object toString. STATUS: " +
                   "ID: " + this.dailyBudget_Id +
                   ", AND money: " + this.money +
                   ". \nEnd of status of DailyBudget object. \n";
    }

}