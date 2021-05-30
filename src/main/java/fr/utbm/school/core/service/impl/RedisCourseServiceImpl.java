package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.dao.impl.RedisCourseDaoImpl;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.service.RedisCourseService;
import org.redisson.api.RTopic;
import org.redisson.api.listener.MessageListener;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Neil Farmer/Ruiqing Zhu
 */
public class RedisCourseServiceImpl implements RedisCourseService {

    private final RedisCourseDaoImpl redisCourseDao;

    public RedisCourseServiceImpl(){
        this.redisCourseDao = new RedisCourseDaoImpl();
    }

    @Override
    public Course getCourseById(String courseId) {
        try {
            return redisCourseDao.getCourseById(courseId);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<Course> getCourseByTitle(String title) {
        try {
            return redisCourseDao.getCourseByTitle(title);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Course> getListCourse() {
        return redisCourseDao.getListCourse();
    }

    @Override
    public void save(@Valid Course course) throws IllegalArgumentException {
        redisCourseDao.save(course);
    }

    @Override
    public void update(@Valid Course course) throws IllegalArgumentException{
        redisCourseDao.update(course);
    }

    @Override
    public void delete(@Valid Course course) throws IllegalArgumentException{
        redisCourseDao.delete(course);
    }

    @Override
    public Long publish(String topic, @Valid Course course) {
        return redisCourseDao.publish(topic, course);
    }

    @Override
    public RTopic subscribe(String topic, MessageListener<? extends Course> listener) {
        return redisCourseDao.subscribe(topic, listener);
    }
}
