package com.example.notification.controller;

import com.example.notification.DTO.NotificationInternalrequestDTO;
import com.example.notification.DTO.NotificationRequestDTO;
import com.example.notification.DTO.NotificationResponseDTO;
import com.example.notification.DTO.ResponseDTO;
import com.example.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService service;

    @PostMapping("/notification")
    public ResponseDTO addNotification(@Valid@RequestBody NotificationRequestDTO dto, @RequestHeader("Authorization")String token){
          return  service.addNotification(token,dto);
    }

    @GetMapping("/notification")
    public List<NotificationResponseDTO>getAllNotification(@RequestHeader("Authorization")String token){
         return  service.getAllNotification(token);
    }

    @DeleteMapping("/notification/{id}")
    public String deleteNotification(@PathVariable Long id,@RequestHeader("Authorization")String token){
        return  service.deleteNotification(token,id);
    }
    @PostMapping("/notificationInternal")
    public ResponseDTO addNotificationInternal(@Valid@RequestBody NotificationInternalrequestDTO dto)throws  Exception{
        return  service.addNotificationInternal(dto);
    }


}
