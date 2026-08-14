package com.example.product.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String productName;

  
     private String description;
     
     @Positive(message = "Price must be a positive value")
     private BigDecimal price;

     @Min(value = 0, message = "Quantity must be zero or a positive integer")
     private int quantity;

   
     @NotNull(message = "Category is required")
     private Long category;


     public ProductRequestDTO() {
     }


     public ProductRequestDTO(@NotBlank(message = "Product name is required") String productName, String description,
            @Positive(message = "Price must be a positive value") BigDecimal price,
            @Min(value = 0, message = "Quantity must be zero or a positive integer") int quantity, Long category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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


     public Long getCategory() {
         return category;
     }


     public void setCategory(Long category) {
         this.category = category;
     }

     


}
