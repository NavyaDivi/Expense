package com.caseStudy.Expense.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expenses")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@ManyToOne
	@JoinColumn(name = "expense_type_id", nullable = false)
	private ExpenseType expenseType;
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private LocalDate date;
	
	
	public Expense(Long id, String name, ExpenseType expenseType, BigDecimal amount, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.expenseType = expenseType;
		this.amount = amount;
		this.date = date;
	}
	
	
	public Expense() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ExpenseType getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
