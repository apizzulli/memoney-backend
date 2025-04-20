package com.budgeter.server.Config;

public class JwtToken {

    private String value;
    private long expiresIn;

    public JwtToken(String value, long expiresIn) {
        this.value = value;
        this.expiresIn = expiresIn;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String token){
        this.value = token;
    }

    public long getExpiresIn(){
        return this.expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
