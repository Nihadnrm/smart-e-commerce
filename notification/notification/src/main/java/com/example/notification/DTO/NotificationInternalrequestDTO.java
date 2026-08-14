package com.example.notification.DTO;

import com.example.notification.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NotificationInternalrequestDTO {

    @NotNull
    private Long userId;
    @NotNull
    private Type type;
    @NotBlank
    private  String message;
    @NotBlank String email;
    @NotNull
    private Long orderId;
    @NotNull
    private BigDecimal totalAmount;

    public NotificationInternalrequestDTO() {
    }

    public NotificationInternalrequestDTO(Long userId, Type type, String message ,String email,Long orderId,BigDecimal totalAmount) {
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.email=email;
        this.orderId=orderId;
        this.totalAmount=totalAmount;
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
