package com.budgeter.server.Controllers;
import com.budgeter.server.Config.JwtService;
import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.User;
import com.budgeter.server.Services.*;
import com.budgeter.server.Repositories.BudgetRepository;
import com.budgeter.server.Repositories.UserRepository;
import com.budgeter.server.Services.EmailService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {

    //private final BudgetService budgetService;
    private final BudgetRepository budgetRepo;
    private final UserRepository userRepo;
    private final BudgetService budgetService;
    private final JwtService jwtService;
    private final EmailService emailService;

    public BudgetController(BudgetRepository budgetRepo, UserRepository userRepo, BudgetService budgetService, JwtService jwtService, EmailService emailService) {
        this.budgetRepo = budgetRepo;
        this.userRepo = userRepo;
        this.budgetService = budgetService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @GetMapping(value="getById/{budgId}")
    public ResponseEntity<Budget> getById(@PathVariable(value="budgId") Long budgId){
        Budget b = budgetService.getById(budgId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @PostMapping(value="/create/{id}")
    public ResponseEntity<Budget> newBudget(@RequestBody Budget newBudget, @PathVariable(value="id") Long userId) {
        budgetService.create(newBudget, userId);
        return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
    }

    @GetMapping(value="/getAll/{userId}")
    public ResponseEntity<Object> getAll( @PathVariable(value="userId") Long userId){
        List<Budget> budgets = budgetService.getAll(userId);
        if(budgets.isEmpty()){
            return new ResponseEntity<>("No budgets", HttpStatus.OK);
        }
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

//    @CrossOrigin(origins="http://localhost:3000")
//    @GetMapping(value="/getBudgets")
//    public List<Budget> getBudgets(@RequestBody User user) {
//        List<Budget> budgets = budgetService.getBudgets(user);
//        return budgets;
//    }

    @PatchMapping(value="/update/name/{budgetId}")
    public ResponseEntity<?> updateName(@PathVariable(value="budgetId") Long budgetId, @RequestBody String newName) {
        Optional<Budget> budg = budgetRepo.findById(budgetId);
        Budget budget = budg.get();
        budget.setName(newName);
        budgetRepo.save(budget);
        return ResponseEntity.ok("Budget updated with new name \"" + newName + "\"");
    }

    @GetMapping(value="/update/total/{budgetId}")
    public ResponseEntity<Budget> updateTotal(@PathVariable(value="budgetId") Long budgetId, @RequestBody double newTotal) {
        Optional<Budget> budg = budgetRepo.findById(budgetId);
        Budget budget = budg.get();
        budget.setTotal(newTotal);
        budgetRepo.save(budget);
        return ResponseEntity.ok(budget);
    }
    @GetMapping(value="/test")
    public void test(){
        budgetService.refresh();
    }

}
