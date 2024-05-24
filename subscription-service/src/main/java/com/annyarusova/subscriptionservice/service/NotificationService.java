package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.mapper.NotificationMapper;
import com.annyarusova.subscriptionservice.repository.NotificationInterfaceDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final SubscriptionService subscriptionService;
    private final JavaMailSender mailSender;
    private final UserService userService;
    @Value(value = "${spring.mail.username}")
    private String from;

    public void sendNotifications() {
        List<NotificationInterfaceDto> notificationList = subscriptionService.getReadyNotifications();
        for (NotificationInterfaceDto notification : notificationList) {
            sendEmail(notification.getEmail(),
                    "Дайджест новых вопросов. Помогите найти ответ!",
                    NotificationMapper.toEmailBody(notification));
            userService.updateLastNotification(notification.getPerson());
        }
    }

    public void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
            log.info("Email sent to {} successfully", toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
