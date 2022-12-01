package com.example.backend.AdditionalExpenses.Repository;

import com.example.backend.AdditionalExpenses.Model.AdditionalExpenses;
import org.springframework.data.repository.CrudRepository;

public interface ExpensesRepository extends CrudRepository<AdditionalExpenses,Long> {
}
