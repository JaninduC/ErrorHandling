package com.example.shop.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderModel {

    private int orderId;

    @NotNull(message = "please add customer ")
    private int customerId;

    private Date orderDate;

    @NotNull(message = "please provide total mount")
    private double totalAmount;

    @NotNull(message = "please provide once of Order item")
    private List<OrderItemModel> orderItem;

}
