package com.example.backend.MonthlyBudget.Model;

import com.example.backend.DailyBudget.Model.DailyBudget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long monthly_Id;
    private LocalDate date;
    private double monthlyMoney;

    //We call one monthlyBudget to many DailyBudget
    @OneToMany
    private Set<DailyBudget> dailyBudgets;

    public MonthlyBudget(double monthlyMoney, Set<DailyBudget> dailyBudgets, LocalDate date) {
        this.monthlyMoney = monthlyMoney;
        this.date = date;
        this.dailyBudgets = dailyBudgets;
    }

    @Override //2811 2022 tilføjet toString override for at debugge
    public String toString(){
        return "This is MonthlyBudget toString. Status: \n" +
                "ID: " + this.monthly_Id
                +", AND date: " + this.date
                +", \nAND money: " + this.monthlyMoney
                +", AND set: " + this.dailyBudgets
                +". \nEnd of this object.";
    }
}
