package com.example.shop.controller;

import com.example.shop.model.auth.Authenticate;
import com.example.shop.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTService jwtService;

    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> SignInWithAuthenticate(@RequestBody @Valid Authenticate auth) throws Exception {
        return new ResponseEntity<>(jwtService.GetToken(auth), HttpStatus.OK);
    }

}
