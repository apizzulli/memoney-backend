package com.budgeter.server.Services;

import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.User;
import com.budgeter.server.Repositories.BudgetRepository;
import com.budgeter.server.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepo;
    private final UserRepository userRepo;

    public BudgetService(BudgetRepository budgetRepo, UserRepository userRepo) {
        this.budgetRepo = budgetRepo;
        this.userRepo = userRepo;
    }

    public Budget create(Budget newBudget, Long userId) {
        User user = userRepo.findById(userId).get();
        user.addBudget(newBudget);
        budgetRepo.save(newBudget);
        return newBudget;
    }

    public List<Budget> getAll(Long userId) {
        User user = userRepo.findById(userId).get();
        return user.getBudgets();
    }

//    public Budget edit(Long id, String name, double total, Map<String,String> categories){
//        Budget budget = budgetRepo.findById(id).get();
//        if(name != null){
//            budget.setName(name);
//        }
//        if(total != null){
//
//        }
//    }
    //    public List<Budget> getBudgets(UserDTO user){
//        return user.getBudgets();
//    }
}
