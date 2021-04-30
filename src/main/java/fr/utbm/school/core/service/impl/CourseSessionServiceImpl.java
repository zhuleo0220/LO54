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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Neil FARMER
 */
@Service
public class CourseSessionServiceImpl implements CourseSessionService {

    @Autowired
    private EntityCourseSessionDao entityCourseSessionDao;

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

    public ArrayList<CourseSession> getListCourseSession(){
        return entityCourseSessionDao.getListCourseSession();
    }

    public int getPercentStudent(Long courseSessionId){
        return entityCourseSessionDao.getPercentStudent(courseSessionId);
    }

    public void saveCourseSession(CourseSession courseSession) throws CourseSessionException{
        entityCourseSessionDao.save(courseSession);
    }

    public void updateCourseSession(CourseSession courseSession){
        entityCourseSessionDao.update(courseSession);
    }
}
