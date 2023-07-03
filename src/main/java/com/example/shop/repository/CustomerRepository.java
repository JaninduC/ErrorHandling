package com.example.shop.repository;

import com.example.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select * from customer c order by c.id limit ?2 OFFSET ?1",nativeQuery = true)
    List<Customer> getAll(int start,int end);
}
