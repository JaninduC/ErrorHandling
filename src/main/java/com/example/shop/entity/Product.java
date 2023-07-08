package com.example.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "price", nullable = false)
    @NotNull(message = "please provide a price for the product")
    private double price;

    @Column(name = "description", nullable = false)
    @NotNull(message = "please provide a description")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "please enter stock")
    private int qty;

}
