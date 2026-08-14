package com.example.order.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>{
    List<Orders>findByUserId(Long userId);
    @Query("SELECT o FROM Orders o WHERE o.totalAmount  = :amount AND userId=:userId")
    List<Orders>findOrderByAmount(BigDecimal amount,Long userId);
}
