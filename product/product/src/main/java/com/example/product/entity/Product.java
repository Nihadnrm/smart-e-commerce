package com.example.product.entity;

import com.example.product.enums.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String productName;

     @Lob
     @Column(columnDefinition = "TEXT")
     private String description;

     @Column(precision = 10, scale = 2)  // colun thil total 10 digit varam ,after decimal point 2 digit varam appol munnil 8 digit varum
     private BigDecimal price;

     private int quantity;

     @ManyToOne
     @JoinColumn(name = "category_id")
     private Category category;

     @Enumerated(EnumType.STRING)
     private Status status;

     private Long adminId;

     @CreationTimestamp
     private LocalDateTime createdAt;

     public Product() {
     }

     public Product(Long id, String productName, String description, BigDecimal price, int quantity, Category category,
            Status status,Long adminId, LocalDateTime createdAt) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
        this.adminId = adminId;
        this.createdAt = createdAt;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getProductName() {
         return productName;
     }

     public void setProductName(String productName) {
         this.productName = productName;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public BigDecimal getPrice() {
         return price;
     }

     public void setPrice(BigDecimal price) {
         this.price = price;
     }

     public int getQuantity() {
         return quantity;
     }

     public void setQuantity(int quantity) {
         this.quantity = quantity;
     }

     public Category getCategory() {
         return category;
     }

     public void setCategory(Category category) {
         this.category = category;
     }

     public Status getStatus() {
         return status;
     }

     public void setStatus(Status status) {
         this.status = status;
     }

     public LocalDateTime getCreatedAt() {
         return createdAt;
     }

     public void setCreatedAt(LocalDateTime createdAt) {
         this.createdAt = createdAt;
     }

     public Long getAdminId() {
         return adminId;
     }

     public void setAdminId(Long adminId) {
         this.adminId = adminId;
     }

    



}
