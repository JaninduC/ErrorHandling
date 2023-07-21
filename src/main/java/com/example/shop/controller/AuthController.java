package com.example.shop.controller;

import com.example.shop.model.auth.Authenticate;
import com.example.shop.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTService jwtService;

    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> signInWithAuthenticate(@RequestBody @Valid Authenticate auth) throws Exception {
        return new ResponseEntity<>(jwtService.getToken(auth), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginInWithAuthenticate(@RequestBody @Valid Authenticate auth) throws Exception {
        return new ResponseEntity<>(jwtService.getToken(auth), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody @Valid Authenticate auth) throws Exception {
        return new ResponseEntity<>(jwtService.getToken(auth), HttpStatus.OK);
    }

    @GetMapping("/")
    public String welcome() {
        return "<h1> Welcome</h1>";
    }


}
