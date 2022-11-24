package com.example.backend.DailyBudget.Model;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class DailyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dailyBudget_Id;

    private long dailyBudgetMoney;

    @ManyToOne
    @JoinColumn(name = "monthlyBudgetId", nullable=false)
    private MonthlyBudget monthlyBudget;


}
