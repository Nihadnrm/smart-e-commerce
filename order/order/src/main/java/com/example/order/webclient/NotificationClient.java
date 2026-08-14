package com.example.order.webclient;

import com.example.order.DTO.NotificationInternalrequestDTO;
import com.example.order.enums.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationClient {
    @Autowired
    WebClient webClient;

@CircuitBreaker(name = "notificationService",fallbackMethod = "fallbackNotification")
    public NotificationInternalrequestDTO addNotification(NotificationInternalrequestDTO dto){
        return  webClient.post().uri("http://localhost:8083/notificationInternal").bodyValue(dto).retrieve().bodyToMono(NotificationInternalrequestDTO.class).block();
    }
    public NotificationInternalrequestDTO fallbackNotification(NotificationInternalrequestDTO dto, Exception ex){
     return  new NotificationInternalrequestDTO(dto.getUserId(),Type.EMAIL,"notification service is down","mail not found",0L,BigDecimal.ZERO);
    }
}
