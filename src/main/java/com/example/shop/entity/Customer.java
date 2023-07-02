package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date dob;

}
