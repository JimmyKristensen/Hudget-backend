package com.example.backend.MonthlyBudget.Model;


import com.example.backend.DailyBudget.Model.DailyBudget;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Date date;

    @OneToMany
    private Set<DailyBudget> dailyBudgets;

    public MonthlyBudget(double monthlyMoney, Date date) {
        this.monthlyMoney = monthlyMoney;
        this.date = date;

    }


}
