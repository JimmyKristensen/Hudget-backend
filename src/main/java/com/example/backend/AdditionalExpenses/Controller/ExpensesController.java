package com.example.backend.AdditionalExpenses.Controller;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import com.example.backend.AdditionalExpenses.Service.ExpensesService;
import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Service.MonthlybudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/expenses")
public class ExpensesController {

    private final ExpensesService service;

    @GetMapping
    public Iterable<AdditionalExpenses> callApi(){return service.findAll();}

    public ExpensesController(ExpensesService service){this.service = service;}

    @GetMapping("/{id}")
    public ResponseEntity<AdditionalExpenses> find(@PathVariable("id") Long id){
        Optional<AdditionalExpenses> additionalExpenses = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Monthly Budget not found".formatted(id))));
        return ResponseEntity.ok().body(additionalExpenses.get());
    }
    @PostMapping
    public ResponseEntity<AdditionalExpenses> create(@RequestBody AdditionalExpenses additionalExpenses) {
        AdditionalExpenses item = service.create(additionalExpenses);
        return ResponseEntity.ok().body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdditionalExpenses> put(@PathVariable("id") Long id, @RequestBody AdditionalExpenses additionalExpenses) {
        return ResponseEntity.ok().body(service.update(id, additionalExpenses));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdditionalExpenses> patch(@PathVariable("id") Long id, @RequestBody AdditionalExpenses additionalExpenses) {
        return ResponseEntity.ok().body(service.update(id, additionalExpenses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdditionalExpenses> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Reservation not found.".formatted(id)));

        AdditionalExpenses delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }
}
