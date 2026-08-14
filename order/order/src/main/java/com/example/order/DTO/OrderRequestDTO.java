package com.example.order.DTO;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class OrderRequestDTO {

   @Valid
   @NotEmpty(message = "product list can not be empty")
   private List<OrderItemRequestDTO>orderItemsList;

   public OrderRequestDTO() {
   }

   public OrderRequestDTO( List<OrderItemRequestDTO> orderItemsList) {
      this.orderItemsList = orderItemsList;
   }

   public List<OrderItemRequestDTO> getOrderItemsList() {
      return orderItemsList;
   }

   public void setOrderItemsList(List<OrderItemRequestDTO> orderItemsList) {
      this.orderItemsList = orderItemsList;
   }


}
