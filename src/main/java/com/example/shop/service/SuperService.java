package com.example.shop.service;

import java.util.List;

public interface SuperService<T> {
     boolean add(T t) throws Exception;

     boolean update(T t) throws Exception;

     boolean delete(int id) throws Exception;

     T find(int id) throws Exception;

     List<T> getAll(int start, int end) throws Exception;
}
