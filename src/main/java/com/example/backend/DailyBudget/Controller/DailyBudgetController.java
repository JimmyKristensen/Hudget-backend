package com.example.backend.DailyBudget.Controller;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.DailyBudget.Service.DailyBudgetService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
