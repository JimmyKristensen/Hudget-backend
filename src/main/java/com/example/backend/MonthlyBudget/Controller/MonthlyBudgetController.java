package com.example.backend.MonthlyBudget.Controller;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Service.MonthlybudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/monthlybudget")
public class MonthlyBudgetController {

    private final MonthlybudgetService service;

    @GetMapping
    public Iterable<MonthlyBudget> callApi(){return service.findAll();}

    public MonthlyBudgetController(MonthlybudgetService service){this.service = service;}

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyBudget> find(@PathVariable("id") Long id){
        Optional<MonthlyBudget> monthlyBudget = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Monthly Budget not found".formatted(id))));
        return ResponseEntity.ok().body(monthlyBudget.get());
    }
    

}
