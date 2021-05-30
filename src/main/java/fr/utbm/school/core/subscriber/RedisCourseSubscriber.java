package fr.utbm.school.core.subscriber;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.service.ClientService;
import fr.utbm.school.core.service.RedisCourseService;
import fr.utbm.school.core.service.impl.RedisCourseServiceImpl;
import fr.utbm.school.core.tools.RedisUtil;
import lombok.extern.log4j.Log4j;
import org.redisson.api.RTopic;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class RedisCourseSubscriber {

    private RedisCourseServiceImpl service = new RedisCourseServiceImpl();

    public RedisCourseSubscriber(){
        service.subscribe("courseTopic",new MessageListener<Course>() {
            @Override
            public void onMessage(CharSequence channel, Course msg) {
                // Action
            }
        });
    }
}
