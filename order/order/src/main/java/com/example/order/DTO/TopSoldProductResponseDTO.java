package com.example.order.DTO;

import java.math.BigDecimal;

public class TopSoldProductResponseDTO {

    private  Long productId;
    private Long totalSold;
    private  String productName;
    private BigDecimal price;

    public TopSoldProductResponseDTO() {
    }

    public TopSoldProductResponseDTO(Long productId, Long totalSold, String productName, BigDecimal price) {
        this.productId = productId;
        this.totalSold = totalSold;
        this.productName = productName;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Long totalSold) {
        this.totalSold = totalSold;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
