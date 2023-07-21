package com.example.shop.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModel {

    private int userId;

    @NotBlank(message = "please enter username")
    private String username;

    @Email(message = "incorrect email format")
    @NotBlank(message = "invalid email")

    private String email;

    @NotBlank(message = "invalid password")
    private String password;


    @NotBlank(message = "please enter role")
    private String role;
}
