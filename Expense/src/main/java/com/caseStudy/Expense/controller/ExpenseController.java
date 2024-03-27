package com.caseStudy.Expense.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caseStudy.Expense.dto.ExpenseRequest;
import com.caseStudy.Expense.dto.ExpenseTypeRequest;
import com.caseStudy.Expense.model.Expense;
import com.caseStudy.Expense.model.ExpenseType;
import com.caseStudy.Expense.service.ExpenseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/expense")
public class ExpenseController {

	private final ExpenseService expenseService;

	@PostMapping("/newExpense")
	@ResponseStatus(HttpStatus.CREATED)
	public String createExpense(@RequestBody ExpenseRequest expenseRequest) {
		expenseService.createExpense(expenseRequest);
		return "Expense Created";
	}

	@PutMapping("/updateExpense/{id}")
	public void updateExpense(@RequestBody Expense expense) {

		expenseService.updateExpense(expense);

	}

	@GetMapping("/getExpense")
	public List<Expense> getExpenses() {
		return expenseService.getExpense();

	}

	@DeleteMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable Long id) {
		
		expenseService.deleteExpense(id);
		return "Expense Deleted!!!!";
	}

	@GetMapping("/getExpensesByDate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Object[]>> getExpensesByDate(
			@RequestParam("startDate") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {

		return expenseService.getExpensesByDate(startDate, endDate);
	}
	
	@PostMapping("/newCategory")
	@ResponseStatus(HttpStatus.CREATED)
	public String newCategory(@RequestBody ExpenseTypeRequest expenseTypeRequest) {
		expenseService.newCategory(expenseTypeRequest);
		return "Category Saved";
		
	}
	
	@GetMapping("/getCategories")
	@ResponseStatus(HttpStatus.OK)
	public List<ExpenseType> getCategories() {
		return expenseService.getCategories();

	}
	
	@GetMapping("/totalAmount")
	@ResponseStatus(HttpStatus.OK)
	public BigDecimal totalExpenses() {	
		return expenseService.totalExpenses();
	}

	
	//getExpenses by categories
	// total amount of expenses
	// sort by date, categories

}
