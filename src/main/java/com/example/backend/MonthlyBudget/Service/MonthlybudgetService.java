package com.example.backend.MonthlyBudget.Service;


import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Repository.MonthlyBudgetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MonthlybudgetService {
    private final MonthlyBudgetRepository repository;
    private final DailyBudgetService dailyService;

    public MonthlybudgetService(MonthlyBudgetRepository repository, DailyBudgetService dailyService) {
        this.repository = repository;
        this.dailyService = dailyService;}

    public Iterable<MonthlyBudget> findAll(){
        return repository.findAll();
    }

    public Optional<MonthlyBudget> find(Long id){
        return repository.findById(id);
    }

    public MonthlyBudget findByDate(String date){
        return repository.findByDate(date);
    }



    public MonthlyBudget create(MonthlyBudget monthlyBudget) {
            LocalDate current = LocalDate.now();
            String getYear = Integer.toString(current.getYear()) + "-" + Integer.toString(current.getMonthValue());
            monthlyBudget.setDate(getYear);

         Set<DailyBudget> listDaily;
        listDaily = dailyService.create(monthlyBudget);

        Set<DailyBudget> target = new HashSet<>(listDaily);
        monthlyBudget.setDailyBudgets(target);
        return repository.save(monthlyBudget);
    }
    public MonthlyBudget update(Long id, MonthlyBudget monthlyBudget) {
        return repository.save(monthlyBudget);
    }


    public MonthlyBudget updateForMoney(Long id, MonthlyBudget monthlyBudget) {
        MonthlyBudget monBuActual;
        if(repository.existsById(id)){ //tjek om MontlhyBudget eksisterer
          Optional<MonthlyBudget> monBuOld = repository.findById(id); //finder  den
          monBuActual = monBuOld.get(); //ændreer den fra optional til rigtig.

            //We want to update all the dailies money that was assosiated with this monthlyBudget

            dailyService.updateMonthlyDailies(monthlyBudget, monBuActual);
        } else {
            System.out.println("Month not found in DB, sout from updateForMoney in MonthlyBudgetService. \n" +
                    "Returning unmodified monthlybudget. Monthlybudget ID: " + id);
            return  monthlyBudget;
        }
        monBuActual.setMonthlyMoney(monthlyBudget.getMonthlyMoney());
        return repository.save(monBuActual);
        }

    public MonthlyBudget delete(long id){
        repository.deleteById(id);
        return null;
    }



}
