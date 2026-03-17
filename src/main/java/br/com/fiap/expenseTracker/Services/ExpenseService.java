package br.com.fiap.expenseTracker.Services;

import br.com.fiap.expenseTracker.Models.Expense;
import br.com.fiap.expenseTracker.Repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    public List<Expense> getExpenses(){
        return repository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id){
        return repository.findById(id);
    }

    public Expense addExpense(Expense expense){
        return repository.save(expense);
    }

    public void deleteExpense(Long id){
        var optionalExpense = getExpenseById(id);
        if(optionalExpense.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found");
        }

        repository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense newExpense){
        var optionalExpense = getExpenseById(id);
        if(optionalExpense.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found");
        }

        newExpense.setId(id);
        return repository.save(newExpense);
    }
}
