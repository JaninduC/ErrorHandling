package com.example.shop.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductModel {


    private int productId;

    @NotNull(message = "please provide a price for the product")
    private double price;


    @NotNull(message = "please provide a description")
    private String description;

    @NotNull(message = "please enter stock")
    private int qty;
}
