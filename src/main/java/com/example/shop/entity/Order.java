package com.example.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "Orders")
public class Order {


    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;


    @NotNull(message = "please add customer ")
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date", nullable = false)
    @NotNull(message = "please provide order date")
    private Date orderDate;

    @Column(name = "total_amount", nullable = false)
    @NotNull(message = "please provide total mount")
    private double totalAmount;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;

}
