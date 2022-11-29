package com.example.backend.DailyBudget.Model;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

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
    private LocalDate date;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "monthly_Id", nullable=false)
//    private MonthlyBudget monthlyBudget;
//
//    @ManyToOne
//    @JoinColumn(name = "monthly_Id", nullable=false)
//    private MonthlyBudget monthlyBudget;

       public DailyBudget(float money, LocalDate date){
        this.money = money;
        this.date = date;

    }

    @Override //2811 2022 hans: givet dailybudget en tostring for debugging
    public String toString(){
           return "This is dailyBudget object toString. STATUS: \n" +
                   "ID: " + this.dailyBudget_Id +
                   ", AND money: " + this.money +
                   ". \nEnd of status.";
    }

}