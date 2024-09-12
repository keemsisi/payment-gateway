package com.core.payment.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class CorePaymentGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorePaymentGatewayApplication.class, args);
    }

}
