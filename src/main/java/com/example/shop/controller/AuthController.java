package com.example.shop.controller;

import com.example.shop.exception.InvalidRefreshToken;
import com.example.shop.exception.InvalidUsernamePasswordException;
import com.example.shop.model.auth.Authenticate;
import com.example.shop.model.auth.RefreshTokenModel;
import com.example.shop.model.auth.Token;
import com.example.shop.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthController(JWTService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> signInWithAuthenticate(@RequestBody @Valid Authenticate auth) throws Exception {
        return new ResponseEntity<>(jwtService.getToken(auth), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginInWithAuthenticate(@RequestBody @Valid Authenticate auth) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        if (authenticate.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.getToken(auth), HttpStatus.OK);
        } else {
            throw new InvalidUsernamePasswordException("invalid username or password !");
        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshToken(@RequestBody @Valid RefreshTokenModel tokenModel) throws InvalidRefreshToken {
        Token refreshToken = jwtService.getRefreshToken(tokenModel.getToken());
        return new ResponseEntity<>(refreshToken, HttpStatus.OK);
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
