/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.Dao.EntityCourseDao;
import fr.utbm.school.core.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private EntityCourseDao entityCourseDao;

    private final RedisCourseServiceImpl redisCourseService;

    public CourseServiceImpl() {
        redisCourseService = new RedisCourseServiceImpl();
    }

    @Cacheable(cacheNames = "courseCache", key = "#idCourse")
    public Course searchCourseById(String idCourse){
        return entityCourseDao.getCourseById(idCourse);
    }

    @Cacheable(cacheNames = "courseCache")
    public ArrayList<Course> getListCourse(){
        return entityCourseDao.getListCourse();
    }

    public ArrayList<Course> getCourseByKeyword(String keyword){
        return entityCourseDao.getCourseByKeyword(keyword);
    }

    @CachePut(value = "courseCache", key = "#course.code")
    public Course saveCourse(Course course) throws SQLException{
        entityCourseDao.save(course);
        this.redisCourseService.save(course);
        return course;
    }

    @CacheEvict(value = "courseCache", allEntries = true)
    public Course updateCourse(Course course){
        entityCourseDao.update(course);
        this.redisCourseService.update(course);
        return course;
    }

    public ArrayList<String> getCourseKeyword(){
        return entityCourseDao.getCourseKeyword();
    }

}
