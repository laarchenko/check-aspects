package com.example.company;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Job {

    @ExecuteAfterBootstrap
    @Scheduled(fixedRate = 2000)
    @SchedulerLock(name = "TaskScheduler_scheduledTask")
    public void reportCurrentTime() {
        log.info("Job executed");
    }
}
