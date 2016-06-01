package com.fictophone.services.records;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {Application.class})
@EnableSwagger
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
