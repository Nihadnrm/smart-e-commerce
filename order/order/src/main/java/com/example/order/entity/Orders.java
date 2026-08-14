package com.example.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.example.order.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Orders {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;

private Long userId;

@Column(precision = 10,scale = 2)
private BigDecimal totalAmount;


@Enumerated(EnumType.STRING)
private  Status orderStatus;

@OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
private List<OrderItems>orderItems;



@CreationTimestamp
private LocalDateTime  CReatedAt;




public Orders() {
}




public Orders(Long id, Long userId, BigDecimal totalAmount, Status orderStatus, List<OrderItems> orderItems,
        LocalDateTime cReatedAt) {
    this.id = id;
    this.userId = userId;
    this.totalAmount = totalAmount;
    this.orderStatus = orderStatus;
    this.orderItems = orderItems;
    CReatedAt = cReatedAt;
}




public Long getId() {
    return id;
}




public void setId(Long id) {
    this.id = id;
}




public Long getUserId() {
    return userId;
}




public void setUserId(Long userId) {
    this.userId = userId;
}




public BigDecimal getTotalAmount() {
    return totalAmount;
}




public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
}




public Status getOrderStatus() {
    return orderStatus;
}




public void setOrderStatus(Status orderStatus) {
    this.orderStatus = orderStatus;
}




public List<OrderItems> getOrderItems() {
    return orderItems;
}




public void setOrderItems(List<OrderItems> orderItems) {
    this.orderItems = orderItems;
}




public LocalDateTime getCReatedAt() {
    return CReatedAt;
}




public void setCReatedAt(LocalDateTime cReatedAt) {
    CReatedAt = cReatedAt;
}




}
