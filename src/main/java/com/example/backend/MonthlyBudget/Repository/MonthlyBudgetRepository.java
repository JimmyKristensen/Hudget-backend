package com.example.backend.MonthlyBudget.Repository;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.repository.CrudRepository;

public interface MonthlyBudgetRepository extends CrudRepository<MonthlyBudget, Long> {
}
