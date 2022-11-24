package com.example.backend.MonthlyBudget.Model;


import com.example.backend.DailyBudget.Model.DailyBudget;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class MonthlyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long monthly_Id;
    private double monthlyMoney;
    private LocalDate date;

    //We call one monthlyBudget to many DailyBudget
    @OneToMany
    private Set<DailyBudget> dailyBudgets;


    public MonthlyBudget(double monthlyMoney, LocalDate date, Set<DailyBudget> dailyBudgets) {
        this.monthlyMoney = monthlyMoney;
        this.date = date;
        this.dailyBudgets = dailyBudgets;
    }
}
