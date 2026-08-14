package com.example.users.dto;

public class RegisterResponseDTO {

  private String message;
  private long userId;

    public RegisterResponseDTO(String message, long userId) {
        this.message = message;
        this.userId = userId;
    }

    public RegisterResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    


}
