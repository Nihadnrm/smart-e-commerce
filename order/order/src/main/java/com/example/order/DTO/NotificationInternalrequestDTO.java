package com.example.order.DTO;


import java.math.BigDecimal;

import com.example.order.enums.Type;

public class NotificationInternalrequestDTO {
   private  Long userId;
    private Type type;
    private  String message;
    private String email;
    private Long orderId;
    private BigDecimal totalAmount;

    public NotificationInternalrequestDTO() {
    }

    public NotificationInternalrequestDTO(Long userId, Type type, String message, String email, Long orderId, BigDecimal totalAmount) {
        this.userId=userId;
        this.type = type;
        this.message = message;
        this.email = email;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
