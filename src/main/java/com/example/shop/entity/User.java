package com.example.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", unique = true, nullable = false)
    @NotBlank(message = "please enter username")
    private String username;

    @Email(message = "increct email format")
    @NotBlank(message = "invalid email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "invalid password")
    @Column(name = "password")
    private String password;


    @Column(name = "role", nullable = false)
    @NotBlank(message = "please enter tole")
    private String role;


}
