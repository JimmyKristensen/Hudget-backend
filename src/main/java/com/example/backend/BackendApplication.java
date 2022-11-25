package com.example.backend;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Repository.DailyBudgetRepository;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Repository.MonthlyBudgetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        //  for (DailyBudget dbud : dailyBudgets ) {      //hans: en metode der laver List<dailyB> om til et set
          //     dailySet.add(dbud);         //jeg har kommenteret denne ud (igen) fordi ... dailybudget skal have et monltybudget som
                             //et af sine konstruktør argumenter, men montylbudget skal OGSÅ have et dailybudget set

          // }          //jeg har laet denne for at kunne give MonthlyBudget-konstruktøren nedenunder i cmd-line runneren et Set

        final List<MonthlyBudget> monthlyBudgets = new ArrayList<>();
        monthlyBudgets.add(new MonthlyBudget(20000, dailySet));
        monthlyBudget.saveAll(monthlyBudgets);

//        final List<DailyBudget> dailyBudgets = new ArrayList<>();
//        dailyBudgets.add(new DailyBudget(2000));
//        dailyBudget.saveAll(dailyBudgets); //1402 har kommenteret dette for at se om det kan køre

    };}


}
