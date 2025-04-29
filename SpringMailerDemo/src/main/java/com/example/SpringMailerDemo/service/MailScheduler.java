package com.example.SpringMailerDemo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MailScheduler {

    private final JavaMailSender javaMailSender;

    public MailScheduler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void sendScheduledEmail() {
        String body="Good morning! This is your scheduled email for " +
                LocalDate.now();
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo("uzaifisani8@gmail.com");
        message.setSubject("Scheduled Email");
        message.setText(body);
        javaMailSender.send(message);
    }
}
