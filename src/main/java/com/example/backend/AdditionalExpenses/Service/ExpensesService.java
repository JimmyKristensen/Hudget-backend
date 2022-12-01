package com.example.backend.AdditionalExpenses.Service;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import com.example.backend.DailyBudget.Model.DailyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ExpensesService {


    //class field
    private final CrudRepository<AdditionalExpenses, Long> repository;

    //constructor
    public ExpensesService(CrudRepository<AdditionalExpenses, Long> repository) {this.repository = repository; }

    //Pls change static away if you find a easier way to call this to DailyServiceCreate
    public static Set<AdditionalExpenses> returnDailySet(){
    Set<AdditionalExpenses> expenses = new HashSet<>();
    return expenses;
    }

    //CRUD
    public Iterable<AdditionalExpenses> findAll(){return repository.findAll();}

    public Optional<AdditionalExpenses> find(Long id){
        return repository.findById(id);
    }

    public AdditionalExpenses create(AdditionalExpenses additionalExpenses){return repository.save(additionalExpenses);
    }

    public AdditionalExpenses update(Long id, AdditionalExpenses additionalExpenses){return repository.save(additionalExpenses);
    }
    public AdditionalExpenses delete(long id){repository.deleteById(id); return null;}
}
