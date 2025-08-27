package com.store.amazing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing
public class AmazingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazingApplication.class, args);
    }

}
