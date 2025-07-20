package com.example.main;

import com.example.config.AppConfig;
import com.example.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PaymentService service = context.getBean(PaymentService.class);
        service.makePayment();
    }
}
