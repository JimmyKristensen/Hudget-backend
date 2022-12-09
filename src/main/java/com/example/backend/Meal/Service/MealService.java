package com.example.backend.Meal.Service;

import com.example.backend.Meal.Model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    } //BUG: cannot patch, only put.
    public Meal delete(Long id) {
        repository.deleteById(id);
        return null;
    }

    public Set<Meal> defaultMealInitialiser() { //default "shape" of Meals for a user.
        String[] mealNameStringArray = {"breakfast", "midday", "afternoon"}; //names for the meals.
        Set<Meal> mealSet = new HashSet<>();//init set
        Meal meal; //init meal
        for (String str : mealNameStringArray) { //for-each loops through the array -- once for each string in the array
            meal = new Meal(str,  33.0f); //creates the meal, using str as the name
            mealSet.add(meal); //adds the meal to the set
        }
        for (Meal m : mealSet) create(m); //saves the meals to the database
        return mealSet; //returns the meal-set to the User-constructor
    }
}
