package com.example.backend.MonthlyBudget.Service;


import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
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
        Set<DailyBudget> listDaily =  dailyService.create();
        Set<DailyBudget> target = new HashSet<>(listDaily);
        monthlyBudget.setDailyBudgets(target);
        monthlyBudget.setDate(LocalDate.now());
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
