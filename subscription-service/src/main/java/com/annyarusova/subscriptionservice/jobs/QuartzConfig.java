package com.annyarusova.subscriptionservice.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetail emailJobDetail() {
        return JobBuilder.newJob().ofType(EmailJob.class)
                .withIdentity("EmailJob", "group1")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger myTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(emailJobDetail())
                .withIdentity("EmailTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(10))
                .build();
    }

    @Bean
    public Scheduler scheduler(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        scheduler.setJobFactory(jobFactory);
        return scheduler;
    }
}
