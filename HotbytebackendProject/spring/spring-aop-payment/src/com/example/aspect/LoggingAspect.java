package com.example.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.PaymentService.makePayment(..))")
    public void logBeforePayment() {
        System.out.println("Payment started...");
    }
    @After("execution(* com.example.service.PaymentService.makePayment(..))")
    public void logAfterPayment() {
        System.out.println("Payment done...");
    }
}
