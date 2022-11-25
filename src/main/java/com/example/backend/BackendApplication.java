package com.example.backend;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Repository.DailyBudgetRepository;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Repository.MonthlyBudgetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import java.sql.Date;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class BackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(MonthlyBudgetRepository monthlyBudget,
                                        DailyBudgetRepository dailyBudget)
    { return (args) ->{


        Set<DailyBudget> dailySet = new HashSet<DailyBudget>(); //denne linje laver bare et tomt Set<dailybudgets>
        // til Monltybudget konstrukren

        LocalDate now = LocalDate.now(); //tilføjet denne så vi kan give Budget-construc "now" istedet for "LocalDate.now()";


        final List<DailyBudget> dailybudgets = new ArrayList<>();
        //dailybudgets.add(new DailyBudget(200, date.getTime()));
        dailybudgets.add(new DailyBudget(2, LocalDate.now()));
        //dailybudgets.add(new DailyBudget(150, date.getTime()));

        dailyBudget.saveAll(dailybudgets);
        for (DailyBudget bd : dailybudgets) {

            dailySet.add(bd);

        }

        final List<MonthlyBudget> monthlyBudgets = new ArrayList<>();
        monthlyBudgets.add(new MonthlyBudget(20000, dailySet));
        monthlyBudget.saveAll(monthlyBudgets);

//        final List<DailyBudget> dailyBudgets = new ArrayList<>();
//        dailyBudgets.add(new DailyBudget(2000));
//        dailyBudget.saveAll(dailyBudgets); //1402 har kommenteret dette for at se om det kan køre

    };}


}
