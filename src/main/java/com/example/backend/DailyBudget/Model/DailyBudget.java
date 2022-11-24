package com.example.backend.DailyBudget.Model;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

//hans 2411 2022
@Getter
@Setter
@NoArgsConstructor
@Entity
public class DailyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dailyBudget_Id;

    private float money;

    private LocalDate day;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "monthly_Id", nullable=false)
    private MonthlyBudget monthlyBudget;

    public DailyBudget(float money, LocalDate day) {
        this.money = money;
        this.day = day;
    }
}
