package com.example.backend.MonthlyBudget.Service;


import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MonthlybudgetService {
    private final CrudRepository<MonthlyBudget, Long> repository;
    private final DailyBudgetService dailyService;

    public MonthlybudgetService(CrudRepository<MonthlyBudget,Long> repository, DailyBudgetService dailyService) {
        this.repository = repository;
        this.dailyService = dailyService;}

    public Iterable<MonthlyBudget> findAll(){return repository.findAll();}

    public Optional<MonthlyBudget> find(Long id){
        return repository.findById(id);
    }

    public MonthlyBudget create(MonthlyBudget monthlyBudget) {
        Set<DailyBudget> listDaily =  dailyService.create(monthlyBudget);
        Set<DailyBudget> target = new HashSet<>(listDaily);
        monthlyBudget.setDailyBudgets(target);

        LocalDate current = LocalDate.now();
        String getYear = Integer.toString(current.getYear())+"/"+Integer.toString(current.getMonthValue());
        monthlyBudget.setDate(getYear);

        return repository.save(monthlyBudget);
    }
    public MonthlyBudget update(Long id, MonthlyBudget monthlyBudget) {
        return repository.save(monthlyBudget);
    }


    public MonthlyBudget delete(long id){
        repository.deleteById(id);
        return null;
    }



}
