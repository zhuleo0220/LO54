package fr.utbm.school.core.dao;

import fr.utbm.school.core.entity.Course;
import org.redisson.api.RTopic;
import org.redisson.api.listener.MessageListener;

import java.util.ArrayList;
import java.util.Collection;

public interface RedisCourseDao {

    Course getCourseById(String courseId);

    Collection<Course> getCourseByTitle(String title) throws NullPointerException;

    ArrayList<Course> getListCourse();

    void save(Course course);

    void update(Course course);

    void delete(Course course);

    Long publish(String topic, Course course);

    RTopic subscribe(String topic, MessageListener<? extends Course> listener);

}
