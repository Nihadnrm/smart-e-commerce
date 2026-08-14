package com.example.notification.service;


import com.example.notification.DTO.NotificationInternalrequestDTO;
import com.example.notification.DTO.NotificationRequestDTO;
import com.example.notification.DTO.NotificationResponseDTO;
import com.example.notification.DTO.ResponseDTO;
import com.example.notification.entity.Notification;
import com.example.notification.enums.Status;
import com.example.notification.enums.Type;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository repo;
    @Autowired
    JwtService jwtService;

    @Autowired
    EmailService emailService;

    public ResponseDTO addNotification(String token, NotificationRequestDTO dto){
        String email=jwtService.extractEmail(token.substring(7));
        List<String> roles= jwtService.extractRole(token.substring(7));
        Long referenceId=jwtService.extractReferenceId(token.substring(7));

        Notification notification=new Notification();
        notification.setUserId(referenceId);
        notification.setType(dto.getType());
        notification.setMessage(dto.getMessage());
        notification.setStatus(Status.SEND);
        Notification save= repo.save(notification);

        return new ResponseDTO("notification send successfully");
    }

    public List<NotificationResponseDTO>getAllNotification(String token){
        String email=jwtService.extractEmail(token.substring(7));
        List<String> roles= jwtService.extractRole(token.substring(7));
        Long referenceId=jwtService.extractReferenceId(token.substring(7));

        List<Notification>list=repo.findByUserId(referenceId);
        List<NotificationResponseDTO>listDto=new ArrayList<>();

        for (Notification i:list){
             listDto.add(new NotificationResponseDTO(i.getType(),i.getMessage(),i.getCreatedAt()));
        }
        return listDto;
    }

    public String deleteNotification(String token,Long id){
        String email=jwtService.extractEmail(token.substring(7));
        List<String> roles= jwtService.extractRole(token.substring(7));
        Long referenceId=jwtService.extractReferenceId(token.substring(7));

        Notification notification=repo.findById(id).orElseThrow(()->new RuntimeException("notification not found"));
         repo.delete(notification);
         return  "notification deleted successfully";
    }

    public ResponseDTO addNotificationInternal( NotificationInternalrequestDTO dto)throws  Exception{


        Notification notification=new Notification();
        notification.setUserId(dto.getUserId());
        notification.setType(dto.getType());
        notification.setMessage(dto.getMessage());
        notification.setStatus(Status.SEND);
        Notification save= repo.save(notification);

        if(notification.getType()== Type.EMAIL){

            File invoice= new File("C:/Users/hp/Pictures/Feedback/{57B52118-8326-4E5D-B4D1-3249DE7B85A2}/Capture001.png");

            emailService.sendMail(dto.getEmail(),"invoice",invoice,dto.getEmail(),dto.getOrderId(),dto.getTotalAmount());

        }
        return new ResponseDTO("notification send successfully");
    }




}
