package com.example.company;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.ReadinessState;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class ExecuteAfterBootstrapPointcutAdvisor extends AbstractPointcutAdvisor {

    private final ApplicationAvailability availability;

    ExecuteAfterBootstrapPointcutAdvisor(ApplicationAvailability availability) {
        super();
        setOrder(HIGHEST_PRECEDENCE + 1);
        this.availability = availability;
    }

    @Override
    public Pointcut getPointcut() {
        return new ComposablePointcut(new AnnotationMatchingPointcut(null, ExecuteAfterBootstrap.class, true));
    }

    @Override
    public Advice getAdvice() {
        return (MethodInterceptor) invocation -> {

            if (availability.getReadinessState() != ReadinessState.ACCEPTING_TRAFFIC) {
                log.info("rejected execution");
                return null;
            }
            else {
                log.info("approved execution");
                return invocation.proceed();
            }
        };
    }
}
