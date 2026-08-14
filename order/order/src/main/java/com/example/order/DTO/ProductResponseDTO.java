package com.example.order.DTO;

import java.math.BigDecimal;





public class ProductResponseDTO {
     private Long id;

     private String productName;

  
     private String description;

     private BigDecimal price;

     private int quantity;

     private String categoryName;




   

     public ProductResponseDTO() {
     }

     public ProductResponseDTO(Long id, String productName, String description, BigDecimal price, int quantity,
            String categoryName) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
       
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

  

   


}

