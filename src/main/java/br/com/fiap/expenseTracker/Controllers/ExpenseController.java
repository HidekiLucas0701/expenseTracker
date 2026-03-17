package br.com.fiap.expenseTracker.Controllers;

import br.com.fiap.expenseTracker.Models.Expense;
import br.com.fiap.expenseTracker.Services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ExpenseService service;

    @GetMapping
    public List<Expense> getExpenses(){
        log.info("Listing all expenses");
        return service.getExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        log.info("Searching expense");
        return service.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        log.info("Adding expense");
        expense = service.addExpense(expense);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
        log.info("deleting expense");
        service.deleteExpense(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense newExpense){
        Expense expense = service.updateExpense(id, newExpense);
        return ResponseEntity.ok(expense);
    }
}
