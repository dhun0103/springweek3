package com.example.springweek3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Springweek3Application {

    public static void main(String[] args) {
        SpringApplication.run(Springweek3Application.class, args);
    }

}
