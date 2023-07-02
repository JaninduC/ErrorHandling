package com.example.shop.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerModel {
    int id;
    @NotBlank(message = "name can't be null")
    private String name;

    @NotBlank(message = "address can't be null")
    private String address;

    @Min(value = 18, message = "can't enter age less than 18")
    @Max(value = 70, message = "can't enter age more than 70")
    private int age;

    @Email(message = "Invalid Email")
    private String email;

    @NotNull(message = "date of birth can't be null")
    private Date dob;
}
