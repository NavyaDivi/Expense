package com.caseStudy.Expense.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.caseStudy.Expense.model.ExpenseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {

	private String name;
    private ExpenseType expenseType;
    private BigDecimal amount;
    private LocalDate date;
   	
}
