package com.budgeter.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("antyspp1@gmail.com");
        message.setTo("antpizzulli11@gmail.com");
        message.setSubject("Test from Spring Boot");
        message.setText("Welcome!");
        try{
            mailSender.send(message);
        }catch (Exception ex) {
            System.out.println("error");
        }
    }

    public String sendAccountCreated(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("antyspp1@gmail.com");
        message.setTo(email);
        message.setSubject("MEMONEY™: Account Created");
        message.setText("Welcome to MEMONEY™!");
        try{
            mailSender.send(message);
        }catch (Exception ex) {
            System.out.println("error");
        }
        return "success";
    }
}