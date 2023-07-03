package com.example.shop.service;

import com.example.shop.exception.CustomerNotFoundException;

import java.util.List;

public interface SuperService<T> {
     boolean add(T t) throws Exception;

     boolean update(T t) throws Exception;

     boolean delete(int id) throws CustomerNotFoundException;

     T find(int id) throws CustomerNotFoundException;

     List<T> getAll(int start, int end) throws Exception;
}
