package com.caseStudy.Expense.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.caseStudy.Expense.dto.ExpenseRequest;
import com.caseStudy.Expense.dto.ExpenseTypeRequest;
import com.caseStudy.Expense.model.Expense;
import com.caseStudy.Expense.model.ExpenseType;
import com.caseStudy.Expense.repository.ExpenseRepository;
import com.caseStudy.Expense.repository.ExpenseTypeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ExpenseService {

	private final ExpenseRepository expenseRepository;
	private final ExpenseTypeRepo expenseTypeRepo;
	public ExpenseService(ExpenseRepository expenseRepository, ExpenseTypeRepo expenseTypeRepo) {
		super();
		this.expenseRepository = expenseRepository;
		this.expenseTypeRepo=expenseTypeRepo;
	}

	public void createExpense(ExpenseRequest expenseRequest) {
		Expense expense = new Expense();	
		String expenseTypeName = expenseRequest.getExpenseType().getExpenseTypeName();
        Optional<ExpenseType> existingExpenseType = expenseTypeRepo.findByExpenseTypeName(expenseTypeName);
        ExpenseType expenseType = existingExpenseType.orElseGet(() -> {
        		ExpenseType newExpenseType = new ExpenseType();
	            newExpenseType.setExpenseTypeName(expenseTypeName);
	            return expenseTypeRepo.save(newExpenseType);
	        });
		
		expenseTypeRepo.save(expenseType);

		expense.setExpenseType(expenseType);
		
		
		expense.setName(expenseRequest.getName());
		expense.setAmount(expenseRequest.getAmount());
		expense.setDate(expenseRequest.getDate());
		
		expenseRepository.save(expense);
	}

	public ResponseEntity<Expense> updateExpense(@RequestBody Expense expenseRequest) {

		Expense expense = expenseRepository.save(expenseRequest);
		return ResponseEntity.ok().body(expense);

	}

	public List<Expense> getExpense() {
		return expenseRepository.findAll();

	}

	public void deleteExpense(Long id) {
		Expense expenseToBeDeleted = findById(id);
		expenseRepository.delete(expenseToBeDeleted);
		

	}
	public Expense findById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sorry, the content you are looking for does not exist."));
    }

	public List<ExpenseType> getCategories() {	
		return expenseTypeRepo.findAll();
	}
		
	//Aggregation 
	
	public List<Object[]> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        // Implement this method based on your repository
        return expenseRepository.findExpensesBetweenDatesGroupByExpenseType(startDate, endDate);
    }

    public ResponseEntity<List<Object[]>> getExpensesByDate(LocalDate startDate, LocalDate endDate) {
    	List<Object[]> expenses = getExpensesByDateRange(startDate, endDate);
 
        //Map<String, List<Expense>> expensesByExpenseType = expenses.stream()
 //               .collect(Collectors.groupingBy(expense -> expense.getExpenseType().getExpenseTypeName()));
        
		/*
		 * for (Map.Entry<String, List<Expense>> entry :
		 * expensesByExpenseType.entrySet()) { String expenseTypeName = entry.getKey();
		 * List<Expense> expenseList = entry.getValue();
		 * 
		 * System.out.println("Expense Type: " + expenseTypeName);
		 * 
		 * for (Expense expense : expenseList) { System.out.println("  Expense ID: " +
		 * expense.getId()); System.out.println("  Amount: " + expense.getAmount());
		 * System.out.println("  Date: " + expense.getDate()); System.out.println(); } }
		 */
        
        return new ResponseEntity<>(expenses,HttpStatus.OK);
    }

	public void newCategory(ExpenseTypeRequest expenseTypeRequest) {
		ExpenseType expenseType= new ExpenseType();
		expenseType.setExpenseTypeName(expenseTypeRequest.getExpenseTypeName());
		
		expenseTypeRepo.save(expenseType);
		
	}
	
	public BigDecimal totalExpenses()
	{
		return expenseRepository.getTotalExpenses();
	}


}
