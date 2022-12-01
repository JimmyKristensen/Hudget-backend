package com.example.backend.MonthlyBudget.Service;


import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MonthlybudgetService {

    DailyBudgetService dailyService;


    private final CrudRepository<MonthlyBudget, Long> repository;

    public MonthlybudgetService(CrudRepository<MonthlyBudget,Long> repository, DailyBudgetService dailyService){this.repository = repository;
    this.dailyService = dailyService;}

    public Iterable<MonthlyBudget> findAll(){return repository.findAll();}

    public Optional<MonthlyBudget> find(Long id){
        return repository.findById(id);
    }



    public MonthlyBudget create(MonthlyBudget monthlyBudget) {

        List<DailyBudget> listDaily =  dailyService.create();
        Set<DailyBudget> target = new HashSet<>(listDaily);
        monthlyBudget = new MonthlyBudget(monthlyBudget.getMonthlyMoney(), target, LocalDate.now());

        return repository.save(monthlyBudget);
    }




    public MonthlyBudget update(Long id, MonthlyBudget monthlyBudget) { return repository.save(monthlyBudget);}

    public MonthlyBudget delete(long id){
        repository.deleteById(id);
        return null;
    }

}
