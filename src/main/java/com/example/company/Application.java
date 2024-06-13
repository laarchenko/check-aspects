package com.example.company;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class
Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
