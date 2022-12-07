package com.example.backend.Meal.Repository;

import com.example.backend.Meal.Model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
}
