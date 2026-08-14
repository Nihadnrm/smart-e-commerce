package com.example.notification.DTO;

import com.example.notification.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationRequestDTO {

    @NotNull
    private Type type;
    @NotBlank
    private  String message;
    @NotBlank
    private String email;

    public NotificationRequestDTO() {
    }

    public NotificationRequestDTO(Type type, String message,String email) {
        this.type = type;
        this.message = message;
        this.email=email;
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
}
