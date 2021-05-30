package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.tools.RedisUtil;
import org.redisson.api.RTopic;
import org.redisson.api.RTopicAsync;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.StatusListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface RedisCourseService {

    Course getCourseById(String courseId);

    Collection<Course> getCourseByTitle(String title);

    ArrayList<Course> getListCourse();

    void save(Course course);

    void update(Course course);

    void delete(Course course);

    Long publish(String topic, Course course);

    RTopic subscribe(String topic, MessageListener<? extends Course> listener);

}
