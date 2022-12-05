package com.example.backend.DailyBudget.Service;

import com.example.backend.AdditionalExpenses.Service.ExpensesService;
import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class DailyBudgetService {

      private Set<DailyBudget> days;
      private Set<DailyBudget> setDays;
      private ExpensesService expService;


    //class field
    private final CrudRepository<DailyBudget, Long> repository;

    //constructor
    public DailyBudgetService(CrudRepository<DailyBudget, Long> repository, ExpensesService expService) {
        this.repository = repository;
        this.expService = expService;
    }

    public Iterable<DailyBudget> findAll(){return repository.findAll();}

    public Optional<DailyBudget> find(Long id){
        return repository.findById(id);
    }

    public Set<DailyBudget> create(MonthlyBudget budget){
        days = new HashSet<>();
        setDays = new HashSet<>();
        LocalDate today = LocalDate.now();

        //Days left in the month
        int daysBetween = daysLeftInMonth();

        //Monthly money split for each day
        float moneySplit = returnDailyValue(budget);

        //Make enteties depending on how many days
        for (int i = 0; i < daysBetween+1; i++) {
            DailyBudget newDailyBudget = new DailyBudget(moneySplit, today, expService.returnDailySet());
            setDays.add(newDailyBudget);
            today = today.plusDays(1);
            days.add(repository.save(newDailyBudget));
        }
        return setDays;
    }

    public Set<DailyBudget> createWithDatesFromMonthly(MonthlyBudget budget){
        days = new HashSet<>();
        setDays = new HashSet<>();
        LocalDate today = LocalDate.now();

        //Days left in the month
        int daysBetween = daysLeftInMonth();

        //Monthly money split for each day
        float moneySplit = returnDailyValue(budget);

        //Make enteties depending on how many days
            today = LocalDate.parse(budget.getDate());
        for (int i = 0; i < daysBetween+1; i++) {
            DailyBudget newDailyBudget = new DailyBudget(moneySplit, today, expService.returnDailySet());
            setDays.add(newDailyBudget);
            today = today.plusDays(1);
            days.add(repository.save(newDailyBudget));
        }
        return setDays;
    }

    public DailyBudget update(Long id, DailyBudget dailyBudget){
        return repository.save(dailyBudget);
    }

    public float returnDailyValue(MonthlyBudget budget){

        //We find todays date, and the end of the months date
        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.from(today);
        LocalDate end   = month.atEndOfMonth();

        //We Stringify it
        String[] currentDay = today.toString().split("-");
        String[] lastDay = end.toString().split("-");
        //We convert the last part to a Integer
        int daysStart = Integer.parseInt(currentDay[2]);
        int daysEnd = Integer.parseInt(lastDay[2]);
        int daysBetween = daysEnd-daysStart;
        float value = (float) budget.getMonthlyMoney();

        return value/daysBetween;
    }

    public int daysLeftInMonth(){

        //We find todays date, and the end of the months date
        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.from(today);
        LocalDate end   = month.atEndOfMonth();

        //We Stringify it
        String[] currentDay = today.toString().split("-");
        String[] lastDay = end.toString().split("-");
        //We convert the last part to a Integer
        int daysStart = Integer.parseInt(currentDay[2]);
        int daysEnd = Integer.parseInt(lastDay[2]);
        return daysEnd-daysStart;
    }

}
