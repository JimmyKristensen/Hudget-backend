package com.example.backend.DailyBudget.Service;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import com.example.backend.AdditionalExpenses.Service.ExpensesService;
import com.example.backend.DailyBudget.Model.DailyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DailyBudgetService {

    static List<DailyBudget> days = new ArrayList<>();
    static List<DailyBudget> setDays = new ArrayList<>();


    //class field
    private final CrudRepository<DailyBudget, Long> repository;

    //constructor
    public DailyBudgetService(CrudRepository<DailyBudget, Long> repository) {this.repository = repository; }

    public Iterable<DailyBudget> findAll(){return repository.findAll();}

    public Optional<DailyBudget> find(Long id){
        return repository.findById(id);
    }

    public List<DailyBudget> create(){

        //We find todays date, and the end of the months date
        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.from(today);
        LocalDate end   = month.atEndOfMonth();

        //We Stringify it
        String[] currentDay = today.toString().split("-");
        String[] lastDay = end.toString().split("-");
        //We convert the last part to a Integer
        Integer daysStart = Integer.parseInt(currentDay[2]);
        Integer daysEnd = Integer.parseInt(lastDay[2]);

        //Days left in the month
        Integer daysBetween = daysEnd-daysStart;

        //Make enteties depending on how many days
        for (int i = 0; i < daysBetween+1; i++) {
            DailyBudget newDailyBudget = new DailyBudget(0, today, ExpensesService.returnDailySet());
            setDays.add(newDailyBudget);
            today = today.plusDays(1);
            days.add(repository.save(newDailyBudget));
        }
        return setDays;
    }

    public DailyBudget update(Long id, DailyBudget dailyBudget){
        return repository.save(dailyBudget);
    }


}
