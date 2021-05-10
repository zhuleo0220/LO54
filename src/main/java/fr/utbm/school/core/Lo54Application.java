package fr.utbm.school.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LO54Application {

    public static void main(String[] args) {
        SpringApplication.run(LO54Application.class, args);
    }

}
