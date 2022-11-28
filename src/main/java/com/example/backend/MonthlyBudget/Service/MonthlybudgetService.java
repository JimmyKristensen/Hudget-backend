package com.example.backend.MonthlyBudget.Service;


import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Optional;

@Service
public class MonthlybudgetService {
    private final CrudRepository<MonthlyBudget, Long> repository;

    public MonthlybudgetService(CrudRepository<MonthlyBudget,Long> repository){this.repository = repository;}

    public Iterable<MonthlyBudget> findAll(){return repository.findAll();}

    public Optional<MonthlyBudget> find(Long id){
        return repository.findById(id);
    }

    public MonthlyBudget create(MonthlyBudget monthlyBudget) {
        return repository.save(monthlyBudget);
    }
    public MonthlyBudget update(Long id, MonthlyBudget monthlyBudget) { return repository.save(monthlyBudget);}

    public MonthlyBudget delete(long id){
        repository.deleteById(id);
        return null;
    }

}
