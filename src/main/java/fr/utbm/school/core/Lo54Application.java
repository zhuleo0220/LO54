package fr.utbm.school.core;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.service.impl.RedisCourseServiceImpl;
import fr.utbm.school.core.subscriber.RedisCourseSubscriber;
import fr.utbm.school.core.tools.RedisUtil;
import org.redisson.api.RTopic;
import org.redisson.api.listener.BaseStatusListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@SpringBootApplication
@EnableCaching
public class Lo54Application {

    public static void main(String[] args) {
        SpringApplication.run(Lo54Application.class, args);

        // create a subscriber for redis
        RedisCourseSubscriber subcriber = new RedisCourseSubscriber();
    }

}
