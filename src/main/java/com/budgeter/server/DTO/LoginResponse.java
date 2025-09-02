package com.budgeter.server.DTO;

import com.budgeter.server.Config.JwtToken;
import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.User;

import java.util.List;

public class LoginResponse {

    private User user;
    private JwtToken token;

    public LoginResponse(User user, JwtToken token) {
        this.user = user;
        this.token = token;
    }

    public LoginResponse(){}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JwtToken getToken() {
        return this.token;
    }

    public void setToken(JwtToken token) {
        this.token = token;
    }

}
