package com.example.product.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.product.enums.Status;




public class ProductResponseDTO {
     private Long id;

     private String productName;

  
     private String description;

     private BigDecimal price;

     private int quantity;

     private String categoryName;

   private Status status;

     private LocalDateTime createdAt;

   

     public ProductResponseDTO() {
     }

     public ProductResponseDTO(Long id, String productName, String description, BigDecimal price, int quantity,
            String categoryName, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
        this.status = status;
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

     public String getCategoryName() {
         return categoryName;
     }

     public void setCategoryName(String categoryName) {
         this.categoryName = categoryName;
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

   


}
