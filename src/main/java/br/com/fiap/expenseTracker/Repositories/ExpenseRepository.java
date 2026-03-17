package br.com.fiap.expenseTracker.Repositories;

import br.com.fiap.expenseTracker.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
