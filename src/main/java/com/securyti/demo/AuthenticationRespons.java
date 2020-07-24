package com.securyti.demo;

public class AuthenticationRespons {
    private final String jwt;

    public AuthenticationRespons(String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return this.jwt;
    }
    
}