package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.Dao.impl.RedisCourseDaoImpl;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.service.RedisCourseService;

import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<Course> getListCourse() {
        return redisCourseDao.getListCourse();
    }

    @Override
    public void save(Course course) throws IllegalArgumentException {
        redisCourseDao.save(course);
    }

    @Override
    public void update(Course course) throws IllegalArgumentException{
        redisCourseDao.update(course);
    }

    @Override
    public void delete(Course course) throws IllegalArgumentException{
        redisCourseDao.delete(course);
    }
}
