package com.example.product.DTO;

public class ResponseDTO {
  String message;
  Long  productId;
  public ResponseDTO() {
  }
  public ResponseDTO(String message, Long productId) {
    this.message = message;
    this.productId = productId;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public Long getProductId() {
    return productId;
  }
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  
  


}
