package com.brockhaus.mainapp;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.Period;

@SpringBootApplication
@EnableFeignClients
public class MainAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainAppApplication.class, args);
    }

}
