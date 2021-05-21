/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.CourseSessionException;
import fr.utbm.school.core.Dao.EntityCourseSessionDao;
import fr.utbm.school.core.service.CourseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Service
public class CourseSessionServiceImpl implements CourseSessionService {

    @Autowired
    private EntityCourseSessionDao entityCourseSessionDao;

    @Cacheable(cacheNames = "courseSessionCache", key = "#idCourseSession")
    public CourseSession searchCourseSessionById(Long idCourseSession){
        return entityCourseSessionDao.getCourseSessionById(idCourseSession);
    }

    public ArrayList<CourseSession> searchCourseSessionByCourseId(String idCourse){
        return entityCourseSessionDao.searchCourseSessionByCourseId(idCourse);
    }

    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId){
        return entityCourseSessionDao.searchCourseSessionByParameter(date, locationId);
    }

    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId, String courseCode){
        return entityCourseSessionDao.searchCourseSessionByParameter(date, locationId, courseCode);
    }

    @Cacheable(cacheNames = "courseSessionCache")
    public ArrayList<CourseSession> getListCourseSession(){
        return entityCourseSessionDao.getListCourseSession();
    }

    public int getPercentStudent(Long courseSessionId){
        return entityCourseSessionDao.getPercentStudent(courseSessionId);
    }

    @CachePut(value = "courseSessionCache", key = "#courseSession.id")
    public CourseSession saveCourseSession(CourseSession courseSession) throws CourseSessionException{
        return entityCourseSessionDao.save(courseSession);
    }

    @CacheEvict(value = "courseSessionCache", allEntries = true)
    public CourseSession updateCourseSession(CourseSession courseSession){
        return entityCourseSessionDao.update(courseSession);
    }
}
