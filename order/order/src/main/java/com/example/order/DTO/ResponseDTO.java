package com.example.order.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.order.enums.Status;

public class ResponseDTO {

    private Long orderId;
    private Status status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public ResponseDTO() {
    }

    public ResponseDTO(Long orderId, Status status, BigDecimal totalAmount,LocalDateTime createdAt) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt=createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    





    



}
