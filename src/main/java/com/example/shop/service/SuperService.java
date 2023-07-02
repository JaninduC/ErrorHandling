package com.example.shop.service;

import java.util.List;

public interface SuperService<T> {
    public boolean add(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(int id) throws Exception;

    public T find(int id) throws Exception;

    public List<T> getAll() throws Exception;
}
