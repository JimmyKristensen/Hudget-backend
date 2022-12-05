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
    private String date;
    private double monthlyMoney;

    //We call one monthlyBudget to many DailyBudget
    @OneToMany
    private Set<DailyBudget> dailyBudgets;

    //this constructor is deprecated -- dailybdugets are automaticlaly constructed now
    public MonthlyBudget(double monthlyMoney, String date) {
        this.monthlyMoney = monthlyMoney;
        this.date = date;
    }

    @Override //2811 2022 tilføjet toString override for at debugge
    public String toString(){
        return "\nThis is MonthlyBudget toString. Status: \n" +
                "MonthlyBudget ID: " + this.monthly_Id
                +", AND Monthly date: " + this.date
                +", AND Monthly money: " + this.monthlyMoney
                +", \nAND set of Daily Budgets: " + this.dailyBudgets
                +". \nEnd of this Monthly object. \n";
    }
    public boolean isDateNull(){ //bruges til at se om et MonthlyBudget-obj har fået en Date med, eller ej
        return this.date == null;
    }

}
