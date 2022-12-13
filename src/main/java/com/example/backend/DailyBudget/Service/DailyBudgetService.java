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

    public Set<DailyBudget> create(MonthlyBudget budget, boolean isItNull){
        setDays = new HashSet<>();
        float moneySplit;
        int daysBetween = 0;
        LocalDate today = LocalDate.now();

        if(isItNull) {
            daysBetween = daysLeftInMonth(true);
            moneySplit = returnDailyValue(budget, true);
            LocalDate start = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth()-1);
            today = start;
        }
        else{
         
            daysBetween = daysLeftInMonth(false);
            moneySplit = returnDailyValue(budget, false);
        }

        //Make entities depending on how many days
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

    public float returnDailyValue(MonthlyBudget budget, boolean wasDateNull){

        //We find todays date, and the end of the months date
        float value = (float) budget.getMonthlyMoney();
        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.from(today);
        LocalDate end   = month.atEndOfMonth();

      if(!wasDateNull){
        today = LocalDate.now();

        //We Stringify it
        String[] currentDay = today.toString().split("-");
        String[] lastDay = end.toString().split("-");

        //We convert the last part to a Integer
        int daysStart = Integer.parseInt(currentDay[2]);
        int daysEnd = Integer.parseInt(lastDay[2]);
        int daysBetween = daysEnd-daysStart;
        return value/daysBetween;}

      //
        else{

        String budgetDate = budget.getDate();
        String[] str = budgetDate.split("-");
        today = LocalDate.of(Integer.parseInt(str[0]),Integer.parseInt(str[1]),2);
        LocalDate getResult = today.withDayOfMonth(today.getMonth().length(today.isLeapYear()));
          System.out.println(getResult);

          return value/getResult.getDayOfMonth();
      }
    }



    public int daysLeftInMonth(boolean wasItNull){
        //We find todays date, and the end of the months date
        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.from(today);
        LocalDate end   = month.atEndOfMonth();

        if(!wasItNull) {
            //We Stringify it
            String[] currentDay = today.toString().split("-");
            String[] lastDay = end.toString().split("-");
            //We convert the last part to a Integer
            int daysStart = Integer.parseInt(currentDay[2]);
            int daysEnd = Integer.parseInt(lastDay[2]);

        return (daysEnd-daysStart);    }
        else{
            LocalDate getResult = today.withDayOfMonth(today.getMonth().length(today.isLeapYear()));
            return getResult.getDayOfMonth()-1;
        }
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


