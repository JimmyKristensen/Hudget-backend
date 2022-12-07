package com.example.backend.Meal.Service;

import com.example.backend.Meal.Model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealService {
    private final CrudRepository<Meal, Long> repository;

    public MealService(CrudRepository<Meal, Long> repository){
        this.repository = repository;
    }
    public Iterable<Meal> findAll(){return repository.findAll();}
    public Optional<Meal> find(long id){return repository.findById(id);}
    public Meal create(Meal meal){return repository.save(meal);}
    public Meal update(Long id, Meal meal){
        return repository.save(meal);
    }
    public Meal delete(Long id) {
        repository.deleteById(id);
        return null;
    }
}
