package com.example.backend.DailyBudget.Repository;

import com.example.backend.DailyBudget.Model.DailyBudget;
import org.springframework.data.repository.CrudRepository;

public interface DailyBudgetRepository extends CrudRepository<DailyBudget,Long> {
}
