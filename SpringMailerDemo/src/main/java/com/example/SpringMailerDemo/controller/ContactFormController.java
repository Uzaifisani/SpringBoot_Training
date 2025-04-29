package com.example.SpringMailerDemo.controller;

import com.example.SpringMailerDemo.entity.ContactForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/contact")
public class ContactFormController {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public ContactFormController(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @PostMapping
    public ResponseEntity<String> ContactFormSubmit(@RequestBody ContactForm contactForm){
        sendEmailForm(contactForm);
        return ResponseEntity.ok("Email sent successfully");
    }

    public void sendEmailForm(ContactForm userContactForm){
        Context context= new Context();
        context.setVariable("name",userContactForm.getName());
        context.setVariable("message",userContactForm.getMessage());
        context.setVariable("time", LocalDateTime.now());

        String body = templateEngine.process("contact-email-template.html",context);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userContactForm.getEmail());
            helper.setSubject("Contact Form Received");
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
