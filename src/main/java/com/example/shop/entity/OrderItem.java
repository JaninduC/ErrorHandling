package com.example.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private int orderItemId;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull(message = "please select a order")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull(message = "Please select product Id")
    private Product product;

    @Column(name = "qty", nullable = false)
    @NotNull(message = "please provide a qty")
    private int qty;

    @Column(name = "subtotal", nullable = false)
    @NotNull(message = "please provide a subtotal")
    private double subTotal;

}
