package com.example.shop.model.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authenticate {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
