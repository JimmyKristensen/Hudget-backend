package com.example.backend.DailyBudget.Model;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

//hans 2411 2022
@Getter
@Setter
@Entity
@NoArgsConstructor
public class DailyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dailyBudget_Id;

    private float dailyMoney;

    private Date day;

    @ManyToOne
    @JoinColumn(name = "monthly_Id", nullable=false)
    private MonthlyBudget monthlyBudget;

}
