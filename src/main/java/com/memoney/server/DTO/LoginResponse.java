package com.memoney.server.DTO;

import com.memoney.server.Config.JwtToken;
import com.memoney.server.Entities.User;

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
