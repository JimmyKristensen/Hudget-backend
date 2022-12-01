package com.example.backend.MonthlyBudget.Service;


import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
        return repository.save(monthlyBudget);
    }
    public Optional<MonthlyBudget> update(Long id, MonthlyBudget monthlyBudget) {
        return repository.findById(id)
                .map(oldItem -> repository.save(oldItem.updateWith(monthlyBudget)));
    }

    public MonthlyBudget delete(long id){
        repository.deleteById(id);
        return null;
    }
    public void createNewDailyBudgets(Set<DailyBudget> dailyBudgets) {
        Iterator newIte = dailyBudgets.iterator();
        while(newIte.hasNext()) dailyService.create((DailyBudget) newIte.next());
    }


}
