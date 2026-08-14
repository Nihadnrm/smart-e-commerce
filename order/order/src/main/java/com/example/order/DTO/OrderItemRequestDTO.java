package com.example.order.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequestDTO {
    
@NotNull (message = "must give product id")   
private Long ProductId;

@Min(value = 1,message = "minimum quantity is 1")
@Max(value = 10 ,message = "maximum quantity is 10")
private int quantity;

public OrderItemRequestDTO() {
}

public OrderItemRequestDTO(Long productId, @Max(value = 10, message = "maximum quantity is 10") int quantity) {
    ProductId = productId;
    this.quantity = quantity;
}

public Long getProductId() {
    return ProductId;
}

public void setProductId(Long productId) {
    ProductId = productId;
}

public int getQuantity() {
    return quantity;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}



}
