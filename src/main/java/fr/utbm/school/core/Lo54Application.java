package fr.utbm.school.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Lo54Application {

    public static void main(String[] args) {
        SpringApplication.run(Lo54Application.class, args);
    }

}
