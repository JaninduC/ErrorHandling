package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from Product c order by c.product_id limit ?2 OFFSET ?1", nativeQuery = true)
    List<Product> getAll(int start, int end);
}
