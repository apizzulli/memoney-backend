package com.budgeter.server.Services;
import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.Transaction;
import com.budgeter.server.Repositories.BudgetRepository;
import com.budgeter.server.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transRepo;
    private final BudgetRepository budgetRepo;
    private final BudgetService budgetService;
    public TransactionService(TransactionRepository transRepo, BudgetRepository budgetRepo, BudgetService budgetService) {
        this.transRepo = transRepo;
        this.budgetRepo = budgetRepo;
        this.budgetService = budgetService;
    }

    public Transaction create(Transaction transaction, Long budgetId){
        Budget budget = budgetRepo.findById(budgetId).get();
        budget.addTransaction(transaction);
        transRepo.save(transaction);
        return transaction;
    }

    public List<Budget> delete(Long transId, Long budgetId) throws HttpClientErrorException{
        Optional<Transaction> trans = transRepo.findById(transId);
        if(trans.isEmpty()){
            return null;
        }
        transRepo.deleteById(transId);
        Optional<Transaction> remaining = transRepo.findById(transId);
        return budgetService.refreshAll();
    }
}
