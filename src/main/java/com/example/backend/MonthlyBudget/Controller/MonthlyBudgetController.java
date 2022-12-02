package com.example.backend.MonthlyBudget.Controller;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
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

    public MonthlyBudgetController(MonthlybudgetService service) {
        this.service = service;
    }


    @GetMapping
    public Iterable<MonthlyBudget> callApi() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyBudget> find(@PathVariable("id") Long id) {
        Optional<MonthlyBudget> monthlyBudget = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Monthly Budget not found".formatted(id))));
        return ResponseEntity.ok().body(monthlyBudget.get());
    }
    @PostMapping()
    public ResponseEntity<MonthlyBudget> create(@RequestBody MonthlyBudget monthlyBudget) {
        /*service.createNewDailyBudgets(monthlyBudget.getDailyBudgets());
        service.create(monthlyBudget);

         */
        service.create(monthlyBudget);
        return ResponseEntity.ok().body(monthlyBudget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyBudget> put(@PathVariable("id") Long id, @RequestBody MonthlyBudget monthlyBudget) {
        return ResponseEntity.ok().body(service.update(id, monthlyBudget));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<MonthlyBudget> patch(@PathVariable("id") Long id, @RequestBody MonthlyBudget monthlyBudget) {
        return ResponseEntity.ok().body(service.update(id, monthlyBudget));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MonthlyBudget> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Monthly Budget not found for deletion".formatted(id)));
        MonthlyBudget monthlyBudget = service.delete(id);
        return ResponseEntity.ok().body(monthlyBudget);
    }


}
