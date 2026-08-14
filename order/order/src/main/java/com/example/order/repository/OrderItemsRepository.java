package com.example.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order.entity.OrderItems;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {

    @Query(value = "SELECT product_id,SUM(quantity)AS totalSold FROM order_items GROUP BY product_id ORDER BY totalSold DESC LIMIT 3",nativeQuery = true)
    List<Object[]>topSoldProduct();
}
