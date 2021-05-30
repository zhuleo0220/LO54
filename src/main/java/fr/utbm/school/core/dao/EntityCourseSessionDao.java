/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.dao;

import fr.utbm.school.core.entity.CourseSession;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface EntityCourseSessionDao {

    CourseSession getCourseSessionById(Long courseSessionId);

    ArrayList<CourseSession> getListCourseSession();

    ArrayList<CourseSession> searchCourseSessionByCourseId(String idCourse);

    ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId);


    ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId, String courseCode);

    int getPercentStudent(Long courseSessionId);

    CourseSession save(CourseSession courseSession);

    CourseSession update(CourseSession courseSession);
}
