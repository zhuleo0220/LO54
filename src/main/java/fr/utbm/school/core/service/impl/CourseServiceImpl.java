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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author neil
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private EntityCourseDao entityCourseDao;

    public Course searchCourseById(String idCourse){
        return entityCourseDao.getCourseById(idCourse);
    }

    @Cacheable(cacheNames = "Course_List", key = "1")
    public ArrayList<Course> getListCourse(){
        return entityCourseDao.getListCourse();
    }

    public ArrayList<Course> getCourseByKeyword(String keyword){
        return entityCourseDao.getCourseByKeyword(keyword);
    }

    public void saveCourse(Course course) throws SQLException{
        entityCourseDao.save(course);
    }

    public void updateCourse(Course course){
        entityCourseDao.update(course);
    }
}
