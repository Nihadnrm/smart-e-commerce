package com.example.notification.DTO;

import java.time.LocalDateTime;

import com.example.notification.enums.Type;

public class NotificationResponseDTO {

    private Type type;
    private  String message;
    private LocalDateTime localDateTime;

    public NotificationResponseDTO() {
    }

    public NotificationResponseDTO(Type type, String message, LocalDateTime localDateTime) {
        this.type = type;
        this.message = message;
        this.localDateTime = localDateTime;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    


}
