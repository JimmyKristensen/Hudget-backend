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
        final List<DailyBudget> dailyBudgets = new ArrayList<>();
        dailyBudgets.add(new DailyBudget(2000,LocalDate.now()));
        dailyBudget.saveAll(dailyBudgets);

        final List<MonthlyBudget> monthlyBudgets = new ArrayList<>();
        monthlyBudgets.add(new MonthlyBudget(20000,LocalDate.now(),));
    };}


}
