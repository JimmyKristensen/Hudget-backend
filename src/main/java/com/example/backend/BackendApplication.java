package com.example.backend;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Repository.DailyBudgetRepository;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Repository.MonthlyBudgetRepository;
import com.example.backend.User.Model.User;
import com.example.backend.User.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

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
                                        DailyBudgetRepository dailyBudget,
                                        UserRepository login
    )
    { return (args) ->{


        Set<DailyBudget> dailySet = new HashSet<DailyBudget>();
        Set<DailyBudget> dailySetEmpty = new HashSet<DailyBudget>();


        LocalDate now = LocalDate.now(); //tilføjet denne så vi kan give Budget-construc "now" istedet for "LocalDate.now()";


        final List<DailyBudget> dailybudgets = new ArrayList<>();

        dailybudgets.add(new DailyBudget(266, now));
        dailybudgets.add(new DailyBudget(223, now));
        dailybudgets.add(new DailyBudget(245, now));
        for (DailyBudget bd : dailybudgets) {
            dailySet.add(bd);
        }
        dailybudgets.add(new DailyBudget(273, now));
        dailybudgets.add(new DailyBudget(212, now)); //LocalDate.now() og now gør det samme. now er kortere. : )
        dailyBudget.saveAll(dailybudgets);



        final List<MonthlyBudget> monthlyBudgets = new ArrayList<>();
        monthlyBudgets.add(new MonthlyBudget(20000, dailySet, now));
        monthlyBudgets.add(new MonthlyBudget(12000,dailySetEmpty,now));
        monthlyBudget.saveAll(monthlyBudgets);

        final List<User> users = new ArrayList<>();
        users.add(new User("Timmie","jegErSuperSej"));
        users.add(new User("Hans","kodeord13"));
        login.saveAll(users);
        System.out.println("CommandLineRunner has completed setup -- Hudget ready to run.");

    };}


}
