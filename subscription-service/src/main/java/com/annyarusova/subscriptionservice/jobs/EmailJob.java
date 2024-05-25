package com.annyarusova.subscriptionservice.jobs;

import com.annyarusova.subscriptionservice.service.NotificationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailJob implements Job {
    @Autowired
    private NotificationService notificationService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Notification sending executed by Quartz Scheduler at: " + LocalDateTime.now());
        notificationService.sendNotifications();
    }
}
