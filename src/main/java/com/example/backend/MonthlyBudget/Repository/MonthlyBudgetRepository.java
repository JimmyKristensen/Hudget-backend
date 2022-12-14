package com.example.backend.MonthlyBudget.Repository;

import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface MonthlyBudgetRepository extends CrudRepository<MonthlyBudget, Long> {
    @Query(value = "SELECT * FROM MONTHLY_BUDGET WHERE CAST(DATE = ?1 AS VARCHAR) AND USER_ID = ?2", nativeQuery = true)
    MonthlyBudget findByDate(String date, long user_Id);
}
