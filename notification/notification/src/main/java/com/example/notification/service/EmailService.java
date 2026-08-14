package com.example.notification.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.math.BigDecimal;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    TemplateEngine templateEngine;

    public void sendMail(String to, String subject, File invoice, String mail, Long orderId, BigDecimal totalAmount)throws Exception{
        Context context=new Context();
        context.setVariable("email",mail);
        context.setVariable("orderId",orderId);
        context.setVariable("totalAmount",totalAmount);
        String html=templateEngine.process("orderConfirmation",context);


        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html,true);

        FileSystemResource file=new FileSystemResource(invoice);
        helper.addAttachment("invoice",file);
        mailSender.send(message);

    }
}
