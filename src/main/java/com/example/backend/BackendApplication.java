package com.example.backend;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import com.example.backend.AdditionalExpenses.Repository.ExpensesRepository;
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
                                        UserRepository login,
                                        ExpensesRepository expenses
    )
    { return (args) ->{

        //Diffrent HashSet, to fulfill the oneToMany requirement asked in the entities
        Set<DailyBudget> dailySet = new HashSet<DailyBudget>();
        Set<DailyBudget> dailySetEmpty = new HashSet<DailyBudget>();
        Set<AdditionalExpenses> additionalExpensesSet = new HashSet<>();
        LocalDate localDate = LocalDate.now();

        LocalDate now = LocalDate.now(); //tilføjet denne så vi kan give Budget-construc "now" istedet for "LocalDate.now()";
        //bliver til yyyy-mm-dd

        final List<AdditionalExpenses> expensesList = new ArrayList<>();
        expensesList.add(new AdditionalExpenses());
        expensesList.add(new AdditionalExpenses());
     //   expenses.saveAll(expensesList);

        final List<DailyBudget> dailybudgets = new ArrayList<>();
        dailybudgets.add(new DailyBudget(266, now,additionalExpensesSet));
        dailybudgets.add(new DailyBudget(223, now,additionalExpensesSet));
        dailybudgets.add(new DailyBudget(245, now,additionalExpensesSet));

   //     dailyBudget.saveAll(dailybudgets);
        for (DailyBudget bd : dailybudgets) {
            dailySet.add(bd);
        }

        List<MonthlyBudget> monthlyBudgetsList = new ArrayList<>();
       // monthlyBudgetsList.add(new MonthlyBudget(2500, now.toString()));
     //   monthlyBudget.saveAll(monthlyBudgetsList); //testkode -- alle kaldene til saveAll er udkommenteret.

        final List<User> users = new ArrayList<>();
        users.add(new User("Timmie","jegErSuperSej"));
        users.add(new User("Hans","kodeord13"));
        login.saveAll(users);
        System.out.println("CommandLineRunner has completed setup -- Hudget ready to run.");
    };}
}
