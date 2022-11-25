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
   // zprivate LocalDate day;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "monthly_Id", nullable=false)
//    private MonthlyBudget monthlyBudget;
//
//    @ManyToOne
//    @JoinColumn(name = "monthly_Id", nullable=false)
//    private MonthlyBudget monthlyBudget;

//    public DailyBudget(float money, //udkommenteret denne for at tjekke om den kan fungere hvis ikke vi giver den et
    //monthly budget
//                       //LocalDate day, //jeg udkommenterer Day fordi det måske er det?
//                       MonthlyBudget monthlyBudget) {
//        this.money = money;
//      //  this.day = day;
//        this.monthlyBudget = monthlyBudget; //jeg har tilfjet montlybudget til konstruktøren for at se om det klan køre

       public DailyBudget(float money, LocalDate date){
        this.money = money;
        this.date = date;
      //  this.day = day;
      //  this.monthlyBudget = monthlyBudget; //jeg har tilfjet montlybudget til konstruktøren for at se om det klan køre
    }

}