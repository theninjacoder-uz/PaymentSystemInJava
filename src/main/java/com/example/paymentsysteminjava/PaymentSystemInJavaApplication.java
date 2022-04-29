package com.example.paymentsysteminjava;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.service.auditing.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PaymentSystemInJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSystemInJavaApplication.class, args);
    }
}
