package com.caseStudy.Expense.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caseStudy.Expense.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	@Query(nativeQuery = true, value = "SELECT et.expense_type_name, SUM(e.amount) AS total_expense " +
            "FROM expenses e " +
            "JOIN expense_type et ON e.expense_type_id = et.expense_type_id " +
            "WHERE e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY et.expense_type_name")
	List<Object[]> findExpensesBetweenDatesGroupByExpenseType(LocalDate startDate, LocalDate endDate);

	@Query(nativeQuery = true, value = "SELECT SUM(e.amount) FROM expenses e")
	BigDecimal getTotalExpenses();

}
