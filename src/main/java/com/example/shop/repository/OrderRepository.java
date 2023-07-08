package com.example.shop.repository;

import com.example.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select * from Orders c order by c.order_id limit ?2 OFFSET ?1", nativeQuery = true)
    List<Order> getAll(int start, int end);
}
