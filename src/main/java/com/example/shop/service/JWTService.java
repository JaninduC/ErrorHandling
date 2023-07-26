package com.example.shop.service;

import com.example.shop.exception.InvalidRefreshToken;
import com.example.shop.model.auth.Authenticate;
import com.example.shop.model.auth.Token;

public interface JWTService {
    Token getToken(Authenticate authenticate) throws Exception;


    public Boolean validateToken(String token, Authenticate userDetails);

    String extractUsername(String token);

    Token getRefreshToken(String token) throws InvalidRefreshToken;
}
