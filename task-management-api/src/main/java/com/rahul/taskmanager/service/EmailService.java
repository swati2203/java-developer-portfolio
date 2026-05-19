package com.rahul.taskmanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendTaskAssignmentEmail(String toEmail, String taskTitle) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(toEmail);
            msg.setSubject("New Task Assigned: " + taskTitle);
            msg.setText("You have been assigned a new task: " + taskTitle);
            mailSender.send(msg);
            log.info("Email sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
        }
    }
}
