package com.example.shop.model.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenModel {
    @NotBlank(message = "invalid token")
//    @Min(value = 32,message = "invalid token")
//    @Max(value = 32,message = "invalid token")
    private String token;
}
