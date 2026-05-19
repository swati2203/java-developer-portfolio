package com.rahul.accountservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
@SpringBootApplication @EnableMethodSecurity
public class AccountServiceApplication {
    public static void main(String[] args) { SpringApplication.run(AccountServiceApplication.class, args); }
}
