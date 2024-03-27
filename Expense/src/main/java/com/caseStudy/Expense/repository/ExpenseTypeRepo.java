package com.caseStudy.Expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseStudy.Expense.model.ExpenseType;

@Repository
public interface ExpenseTypeRepo extends JpaRepository<ExpenseType, Long>{

	Optional<ExpenseType> findByExpenseTypeName(String expenseTypeName);

}
