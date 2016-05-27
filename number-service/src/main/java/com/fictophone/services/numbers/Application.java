package com.fictophone.services.numbers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {Application.class})
//@EnableRabbit
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
