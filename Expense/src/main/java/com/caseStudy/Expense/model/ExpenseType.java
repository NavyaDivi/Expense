package com.caseStudy.Expense.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_type")

public class ExpenseType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseTypeId;
	@Column(nullable = false, unique = true)
	private String expenseTypeName;

}
