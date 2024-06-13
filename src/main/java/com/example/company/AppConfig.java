package com.example.company;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }

    @Bean
    ExecuteAfterBootstrapPointcutAdvisor executeAfterBootstrapPointcutAdvisor(@Lazy ApplicationAvailability availability) {
        return new ExecuteAfterBootstrapPointcutAdvisor(availability);
    }
}
