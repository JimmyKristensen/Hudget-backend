package com.example.backend.DailyBudget.Service;

import com.example.backend.AdditionalExpenses.Service.ExpensesService;
import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.Meal.Service.MealService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class DailyBudgetService {

      private  Set<DailyBudget> setDays;
      private  ExpensesService expService;
      private  MealService mealService;

    //class field
    private final CrudRepository<DailyBudget, Long> repository;

    //constructor
    public DailyBudgetService(CrudRepository<DailyBudget, Long> repository,
                              ExpensesService expService,
                              MealService mealService) {
        this.repository = repository;
        this.expService = expService;
        this.mealService = mealService;
    }

    public Iterable<DailyBudget> findAll(){return repository.findAll();}

    public Optional<DailyBudget> find(Long id){
        return repository.findById(id);
    }

    public Set<DailyBudget> create(MonthlyBudget budget){
        setDays = new HashSet<>();
        LocalDate today = LocalDate.now();

        //Days left in the month
        int daysBetween = daysLeftInMonth();

        //Monthly money split for each day
        float moneySplit = returnDailyValue(budget);

        //Make enteties depending on how many days
        for (int i = -1; i < daysBetween; i++) {
            DailyBudget newDailyBudget = new DailyBudget(
                    moneySplit,
                    today,
                    expService.returnDailySet(),
                    mealService.defaultMealInitialiser()
            );
            setDays.add(newDailyBudget);
            today = today.plusDays(1);
            setDays.add(repository.save(newDailyBudget));
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

        return (daysEnd-daysStart);
    }

    //Update dailyBudget.money from the new updated MonthlyBudget.money
    public Set<DailyBudget> updateMonthlyDailies(MonthlyBudget monthly, MonthlyBudget oldData){

        Set<DailyBudget> newDailies = oldData.getDailyBudgets();

        //POSSIBLE ERROR: changing money after a day or two since the programs old version has
        //techically more days than the newer one
        if(newDailies.size() > 1){
        float dailyBudget = (float) monthly.getMonthlyMoney()/newDailies.size();
        for(DailyBudget db : newDailies){
            db.setMoney(dailyBudget);
            newDailies.add(db);
        repository.save(db);

    }} else{
            System.out.println("Funny error Code in the updateDailyMoney");
            }
        return newDailies;
        }

}


