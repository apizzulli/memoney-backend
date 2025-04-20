package com.budgeter.server.DTO;

import com.budgeter.server.Config.JwtToken;
import com.budgeter.server.Entities.Budget;

import java.util.List;

public class LoginDTO {

    private Long userId;
    private JwtToken token;
    private List<Budget> budgets;

    public LoginDTO(Long userId, List<Budget> budgets,JwtToken token) {
        this.userId = userId;
        this.token = token;
        this.budgets = budgets;
    }

    public LoginDTO(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public JwtToken getToken() {
        return token;
    }

    public void setToken(JwtToken token) {
        this.token = token;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

}
