package com.example.backend.MonthlyBudget.Model;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.User.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long monthly_Id;
    private String date;
    private double monthlyMoney;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //We call one monthlyBudget to many DailyBudget
    @OneToMany //We call one monthlyBudget to many DailyBudget
    private Set<DailyBudget> dailyBudgets;

    //this constructor is deprecated -- dailybdugets are automaticlaly constructed now
    public MonthlyBudget(double monthlyMoney, String date, User user) {
        this.monthlyMoney = monthlyMoney;
        this.date = date;
        this.user = user;
    }

    @Override //2811 2022 tilføjet toString override for at debugge
    public String toString(){
        return "\nThis is MonthlyBudget toString. Status: \n" +
                "MonthlyBudget ID: " + this.monthly_Id
                +", AND Monthly date: " + this.date
                +", \nAND Monthly money: " + this.monthlyMoney
                +", AND set of Daily Budgets: " + this.dailyBudgets
                +". \nEnd of this Monthly object. \n";
    }

    @JsonIgnore //0812 2022 json should NOT bring this value via REST
    public boolean isDateNull(){ //bruges til at se om et MonthlyBudget-obj har fået en Date med, eller ej
        return this.date == null; //returner TRUE hvis der ingen Date er
    } //og FALSE hvis der ER en Date.

}
