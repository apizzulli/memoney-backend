package com.budgeter.server.Services;
import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.Transaction;
import com.budgeter.server.Repositories.BudgetRepository;
import com.budgeter.server.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transRepo;
    private final BudgetRepository budgetRepo;

    public TransactionService(TransactionRepository transRepo, BudgetRepository budgetRepo) {
        this.transRepo = transRepo;
        this.budgetRepo = budgetRepo;
    }

    public Transaction create(Transaction transaction, Long budgetId){
        Budget budget = budgetRepo.findById(budgetId).get();
        budget.addTransaction(transaction);
        transRepo.save(transaction);
        return transaction;
    }
}
