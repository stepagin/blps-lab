package com.annyarusova.subscriptionservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final SubscriptionService subscriptionService;
    private final JavaMailSender mailSender;
    @Value(value = "${spring.mail.username}")
    private String from;

    public void sendNotifications() {
        // TODO реализовать нотификацию (дайджест) на почту через MimeMailMessage
        // TODO настроить нотификацию каждые 3-5 минут в QUARTZ, чтобы можно было проверить на паре
    }

    public void sendEmail(String toEmail, String subject, String body) {
        MimeMailMessage message = new MimeMailMessage(mailSender.createMimeMessage());
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message.getMimeMessage());
        log.info("Email sent to {} successfully", toEmail);
    }
}
