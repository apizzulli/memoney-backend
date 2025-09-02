package com.budgeter.server.Controllers;

import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.Transaction;
import com.budgeter.server.Entities.User;
import com.budgeter.server.Repositories.BudgetRepository;
import com.budgeter.server.Repositories.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.budgeter.server.Services.TransactionService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository transRepo;
    private final BudgetRepository budgetRepo;
    private final TransactionService transactionService;

    public TransactionController(TransactionRepository transRepo, BudgetRepository budgetRepo, TransactionService transactionService) {
        this.transRepo = transRepo;
        this.budgetRepo = budgetRepo;
        this.transactionService = transactionService;
    }

    @GetMapping("/test")
    public String test(){
        return "Hello from /transactions/test";
    }
    //@CrossOrigin(origins="http://localhost:3000")
    @PostMapping(value="/add/{budgetId}")
    public Budget addTransaction(@PathVariable(value="budgetId") Long budgetId, @RequestBody Transaction newTrans){
        Budget budget = budgetRepo.findById(budgetId).get();
        budget.setSpent(budget.getSpent()+ newTrans.getAmount());
        budget.addTransaction(newTrans);
        transRepo.save(newTrans);
        return budget;
    }

    @GetMapping(value="/get/{budgetId}")
    public List<Transaction> getTransactions(@PathVariable(value="budgetId") Long budgetId){
        Budget budget = budgetRepo.findById(budgetId).get();
        return budget.getTransactions();
    }

    @PostMapping(value="/delete/{transId}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable(value="transId") Long transId){
        boolean success = transactionService.delete(transId);
        HttpStatus status = HttpStatus.NO_CONTENT;
        if(!success){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
//        return null;
//        Budget budget = budgetRepo.findById(budgetId).get();
//        return budget.getById();
//        return null;
    }

}
