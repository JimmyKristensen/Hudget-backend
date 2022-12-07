package com.example.backend.User.Service;

import com.example.backend.Meal.Service.MealService;
import com.example.backend.User.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final CrudRepository<User, Long> repository;
    private final MealService mealService;

    //constructor
    public UserService(CrudRepository<User, Long> repository, MealService mealService) {
        this.repository = repository;
        this.mealService = mealService;
    }

    public Iterable<User> findAll(){return repository.findAll();}

    public Optional<User> find(Long id){
        return repository.findById(id);
    }

    public User create(User user) {
       user.setUserMeal(mealService.defaultMealInitialiser());
        return repository.save(user);
    }

}
