package com.example.shop.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderItemModel {

    private int orderItemId;

    @NotNull(message = "Please select product Id")
    private int productId;

    @NotNull(message = "please provide a subtotal")
    private double subTotal;


    @NotNull(message = "please provide a order id")
    private int orderId;


    @NotNull(message = "please provide a qty")
    private int qty;


}
