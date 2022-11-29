package com.example.backend.MonthlyBudget.Controller;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.MonthlyBudget.Service.MonthlybudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/monthlybudget")
public class MonthlyBudgetController {

    private final MonthlybudgetService service;
    private final DailyBudgetService dailyService;

    public MonthlyBudgetController(MonthlybudgetService service, DailyBudgetService dailyService){
        this.dailyService = dailyService;
        this.service = service;}

    @GetMapping
    public Iterable<MonthlyBudget> callApi(){return service.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyBudget> find(@PathVariable("id") Long id){
        Optional<MonthlyBudget> monthlyBudget = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Monthly Budget not found".formatted(id))));
        return ResponseEntity.ok().body(monthlyBudget.get());
    }
    @PostMapping()
    public ResponseEntity<MonthlyBudget> create(@RequestBody MonthlyBudget monthlyBudget) {
        Iterator newIte = monthlyBudget.getDailyBudgets().iterator();
        while(newIte.hasNext()) dailyService.create((DailyBudget) newIte.next());
        MonthlyBudget budgetItem = service.create(monthlyBudget);
        return ResponseEntity.ok().body(budgetItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyBudget> put(@PathVariable("id") Long id,
                                               @RequestBody MonthlyBudget monthlyBudget){
        Iterator newIte = monthlyBudget.getDailyBudgets().iterator();
        while(newIte.hasNext()) dailyService.create((DailyBudget) newIte.next());
        return ResponseEntity.ok().body(service.update(id, monthlyBudget));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MonthlyBudget> delete(@PathVariable("id") Long id) {
        service.find(id).orElseThrow(() -> new RuntimeException("Monthly Budget not found for deletion".formatted(id)));
        MonthlyBudget monthlyBudget = service.delete(id);
        return ResponseEntity.ok().body(monthlyBudget);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<MonthlyBudget> patch(@PathVariable("id") Long id,
//                                               @RequestBody MonthlyBudget monthlyBudget) {
//            Optional<MonthlyBudget> extantMonthlyBudget = Optional.of(service.find(id)
//                .orElseThrow(() -> new RuntimeException("Monthly Budget not found for Patch.".formatted(id))));
//            extantMonthlyBudget.get(monthlyMoney);
            //2811 2022 jeg vil hente budgettet og se på hvad for værdier det har
            //            if (extantMonthlyBudget.isPresent()) {
//                MonthlyBudget newBudg = new MonthlyBudget();
//                newBudg = extantMonthlyBudget;
//            }
//    }

}
