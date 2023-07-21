package com.example.shop.controller;


import com.example.shop.model.UserModel;
import com.example.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private UserService service;

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> addUser(@RequestBody @Valid UserModel user) throws Exception {
        return new ResponseEntity<>(service.add(user), HttpStatus.OK);
    }
}
