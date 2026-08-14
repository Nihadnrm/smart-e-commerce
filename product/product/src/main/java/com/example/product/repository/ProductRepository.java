package com.example.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
     List<Product> findProductByName(String productName);

   @Query("SELECT p FROM Product p WHERE p.category.categoryName= :categoryName")
   List<Product>findProductBYCategory(String categoryName);
   

   List<Product>findProductByPriceBetween(Long min,Long max);

}
