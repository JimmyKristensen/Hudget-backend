package com.example.backend.Meal.Controller;

import com.example.backend.Meal.Model.Meal;
import com.example.backend.Meal.Service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("api/v1/meal")
@RestController
public class MealController {
    private final MealService service;

    public MealController(MealService service){this.service = service;}

    @GetMapping
    public Iterable<Meal> getAllMeals() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> findById(@PathVariable("id") Long id) {
        Optional<Meal> meal = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("meal not found.")));
        return ResponseEntity.ok().body(meal.get());
    }

    @PostMapping
    public ResponseEntity<Meal> create(@RequestBody Meal meal) {
        return ResponseEntity.ok().body(service.create(meal));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Meal> put(@PathVariable("id") Long id, @RequestBody Meal meal) {
        return ResponseEntity.ok().body(service.update(id, meal));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Meal> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
