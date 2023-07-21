package com.example.shop.service;

import com.example.shop.model.CustomerModel;

import java.util.List;

public interface CustomerService extends SuperService<CustomerModel> {
    List<CustomerModel> getAll() throws Throwable;
}
