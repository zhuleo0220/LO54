/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.CourseSessionException;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface CourseSessionService {
    CourseSession searchCourseSessionById(Long idCourseSession);

    ArrayList<CourseSession> searchCourseSessionByCourseId(String idCourse);

    ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId);

    ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId, String courseCode);

    ArrayList<CourseSession> getListCourseSession();

    int getPercentStudent(Long courseSessionId);

    CourseSession saveCourseSession(CourseSession courseSession) throws CourseSessionException;

    CourseSession updateCourseSession(CourseSession courseSession);
}
