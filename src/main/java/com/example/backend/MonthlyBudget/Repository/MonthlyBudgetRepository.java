package com.example.backend.MonthlyBudget.Repository;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MonthlyBudgetRepository extends CrudRepository<MonthlyBudget, Long> {
    @Query(value = "SELECT * FROM MONTHLY_BUDGET  WHERE CAST(DATE = DATE AS VARCHAR) AND user_Id = user_Id", nativeQuery = true)
    MonthlyBudget findByDate(String date, long user_Id);
}
