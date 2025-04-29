package com.example.SpringMailerDemo.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    private final Map<String,String> users=Map.of("uzaifisani8@gmail.com","123456");

    private final JavaMailSender mailSender;

    public LoginController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> loginDetails){
        String email= loginDetails.get("email");
        String password=loginDetails.get("password");

        if(users.containsKey(email) && users.get(email).equals(password)){
            sendLoginEmail(email);
            return new ResponseEntity<>("Login Successfull, Email Sent", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Login Failed",HttpStatus.UNAUTHORIZED);
        }
    }

    public void sendLoginEmail(String toEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Login Notification");
        message.setText("You have successfully logged in.");
        mailSender.send(message);
    }
}
