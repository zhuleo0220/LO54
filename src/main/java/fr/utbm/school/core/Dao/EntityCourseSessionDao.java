/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.CourseSessionException;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *
 * @author neil
 */
public interface EntityCourseSessionDao {

    public CourseSession getCourseSessionById(Long courseSessionId);

    public ArrayList<CourseSession> getListCourseSession();

    public ArrayList<CourseSession> searchCourseSessionByCourseId(String idCourse);

    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId);


    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId, String courseCode);

    public int getPercentStudent(Long courseSessionId);

    public void save(CourseSession courseSession) throws CourseSessionException;

    public void update(CourseSession courseSession);
}
