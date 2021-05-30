/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.dao.EntityCourseDao;
import fr.utbm.school.core.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
    public Course saveCourse(@Valid Course course) throws SQLException{

        // Save in JavaDB
        entityCourseDao.save(course);

        // Save the liveObject in Redis
        // Publish in the topic : courseTopic
        this.redisCourseService.publish("courseTopic", course);
        return course;
    }

    @CacheEvict(value = "courseCache", allEntries = true)
    public Course updateCourse(@Valid Course course){
        entityCourseDao.update(course);
        this.redisCourseService.update(course);
        return course;
    }

    public ArrayList<String> getCourseKeyword(){
        return entityCourseDao.getCourseKeyword();
    }

    public Integer getNbPageNeeded(){
        return this.entityCourseDao.getNbPageNeeded();
    }

    public Integer getNbPageNeeded(String keyword){
        return this.entityCourseDao.getNbPageNeeded(keyword);
    }

    public ArrayList<Course> getListCourse(Integer pageNumber){
        return this.entityCourseDao.getListCourse(pageNumber);
    }

    public ArrayList<Course> getCourseByKeyword(String keyword, Integer pageNumber){
        return this.entityCourseDao.getCourseByKeyword(keyword, pageNumber);
    }

}
