package com.example.companieskafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CompaniesKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompaniesKafkaApplication.class, args);
    }

}
