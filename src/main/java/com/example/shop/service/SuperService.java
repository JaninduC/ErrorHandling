package com.example.shop.service;

import com.example.shop.exception.CustomerNotFoundException;

import java.util.List;

public interface SuperService<T> {
    public boolean add(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(int id) throws CustomerNotFoundException;

    public T find(int id) throws CustomerNotFoundException;

    public List<T> getAll(int start, int end) throws Exception;
}
