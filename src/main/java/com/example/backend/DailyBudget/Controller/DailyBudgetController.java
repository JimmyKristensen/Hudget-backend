package com.example.backend.DailyBudget.Controller;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/v1/dailybudget")
public class DailyBudgetController {

    private final DailyBudgetService service;

    public DailyBudgetController(DailyBudgetService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<DailyBudget> getAllDailyBudget() {
        return service.findAll();

    }
    @GetMapping("/{id}")
    public ResponseEntity<DailyBudget> find(@PathVariable("id") Long id){
        Optional<DailyBudget> customer = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found.".formatted(id))));
        return ResponseEntity.ok().body(customer.get());
    }

    @PostMapping
    public ResponseEntity<Set<DailyBudget>> create(@RequestBody DailyBudget dailyBudget) {
        Set<DailyBudget> item = service.create();
        return ResponseEntity.ok().body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyBudget> put(@PathVariable("id") Long id, @RequestBody DailyBudget dailyBudget) {
        return ResponseEntity.ok().body(service.update(id, dailyBudget));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DailyBudget> patch(@PathVariable("id") Long id, @RequestBody DailyBudget dailyBudget) {
        return ResponseEntity.ok().body(service.update(id, dailyBudget));
    }



}
