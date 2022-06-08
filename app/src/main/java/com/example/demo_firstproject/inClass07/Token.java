package com.example.demo_firstproject.inClass07;

public class Token {
    private final Boolean auth;
    private final String token;


    public Token(Boolean auth, String token) {
        this.auth = auth;
        this.token = token;
    }

    public boolean getAuth() {
        return auth;
    }

    public String getToken() {
        return token;
    }
}
