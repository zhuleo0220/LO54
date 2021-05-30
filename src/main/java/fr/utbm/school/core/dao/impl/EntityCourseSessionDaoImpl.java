/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.dao.impl;

import fr.utbm.school.core.dao.EntityClientDao;
import fr.utbm.school.core.dao.EntityCourseSessionDao;
import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.entity.CourseSession;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


/**
 *  Implementation of the DAO (Data Access Object) of
 *  {@link CourseSession}
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Repository
@Transactional
public class EntityCourseSessionDaoImpl implements EntityCourseSessionDao {

    @PersistenceContext
    private EntityManager entityManager ;

    @Autowired
    private EntityClientDao entityClientDao;

    /**
     * Method to get a course session by his id
     *
     * @param courseSessionId of the course session searched
     * @return course session with the given id
     */
    public CourseSession getCourseSessionById(@NonNull Long courseSessionId){
        log.info("The course session with the id=" + courseSessionId + " requested");

        return entityManager.find(CourseSession.class, courseSessionId);
    }

    /**
     * Method to get all course session
     *
     * @return list of all course
     */
    public ArrayList<CourseSession> getListCourseSession(){
        log.info("All course session requested");

        ArrayList<CourseSession> listCourseSession = new ArrayList<CourseSession>();

        // Create the query
        Query q = entityManager.createQuery("from CourseSession CSS order by CSS.startDate");

        // Get the result of the query
        listCourseSession = (ArrayList<CourseSession>) q.getResultList();

        return listCourseSession;
    }

    /**
     * Get course session link to a course
     *
     * @param idCourse of the course we looked for the course session linked to it
     * @return list of course session linked to the given course id
     */
    public ArrayList<CourseSession> searchCourseSessionByCourseId(@NonNull String idCourse){
        log.info("The course session of course with the id=" + idCourse + " requested");

        // Create the query
        Query query = entityManager.createQuery("FROM CourseSession CSS WHERE CSS.course.code = :courseId order by CSS.startDate");
        query.setParameter("courseId", idCourse);

        // Get the result of teh query
	    ArrayList<CourseSession> courseSessionList = new ArrayList<CourseSession>();
        courseSessionList = (ArrayList<CourseSession>) query.getResultList();

        return courseSessionList;
    }

    /**
     * Method to get a course session by giving parameter
     *
     * @param date of the course session searched for
     * @param locationId of the course session searched for
     * @return List of course session
     */
    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId){
        log.info("Course session search for giving parameter");

        // Create the query string
        String sqlQuery = "FROM CourseSession CSS";
        if(date != null && locationId != null){
            sqlQuery += " WHERE CSS.location.id = :locationId and YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(date != null){
            sqlQuery += " WHERE YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(locationId != null){
            sqlQuery += " WHERE CSS.location.id = :locationId";
        }

        // Add the group by and order by clause to display it more properly
        sqlQuery += " ORDER BY CSS.course.code, CSS.startDate";

        // Build the query
	    Query query = entityManager.createQuery(sqlQuery);

	    // Add the parameter
        if(date != null){
            int yearSession = date.getYear() + 1900;
            query.setParameter("dateYear", yearSession);
            int monthSession = date.getMonth() + 1;
            query.setParameter("dateMonth", monthSession);
            query.setParameter("dateDay", date.getDate());
        }
        if(locationId != null){
            query.setParameter("locationId", locationId);
        }

        // Get the results
	    ArrayList<CourseSession> courseSessionList = new ArrayList<CourseSession>();
        courseSessionList = (ArrayList<CourseSession>) query.getResultList();

        return courseSessionList;
    }

    /**
     * Method to search course session by parameters
     *
     * if none course have been indicated
     * it will go to the method
     * {@link EntityCourseSessionDaoImpl#searchCourseSessionByParameter(Timestamp date, Long locationId)}
     *
     * @param date of the session searched
     * @param locationId of the session searched
     * @param courseCode of the session searched
     * @return List of course session with the corresponding parameter
     */
    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId, String courseCode){
        log.info("Course session search for giving parameter");

        // if none course have been indicated
        if(courseCode == null){
            // Can use an other method
            return this.searchCourseSessionByParameter(date, locationId);
        }

        // Create the query string
        String sqlQuery = "FROM CourseSession CSS WHERE CSS.course.code = :courseCode";
        if(date != null && locationId != null){
            sqlQuery += " AND CSS.location.id = :locationId AND YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(date != null){
            sqlQuery += " AND YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(locationId != null){
            sqlQuery += " AND CSS.location.id = :locationId";
        }

        // Add the group by and order by clause to display it more properly
        sqlQuery += " ORDER BY CSS.course.code, CSS.startDate";

        // Build the query
	    Query query = entityManager.createQuery(sqlQuery);
        query.setParameter("courseCode", courseCode);

        // Add the parameter to the query
        if(date != null){
            int yearSession = date.getYear() + 1900;
            query.setParameter("dateYear", yearSession);
            int monthSession = date.getMonth() + 1;
            query.setParameter("dateMonth", monthSession);
            query.setParameter("dateDay", date.getDate());
        }
        if(locationId != null){
            query.setParameter("locationId", locationId);
        }

	    ArrayList<CourseSession> courseSessionList = new ArrayList<CourseSession>();
        courseSessionList = (ArrayList<CourseSession>) query.getResultList();

        return courseSessionList;
    }

    /**
     * Method to know how filled a course session is
     *
     * @param courseSessionId to look for
     * @return A percent of the filling of a course
     */
    public int getPercentStudent(@NonNull Long courseSessionId){
        log.info("The percent of course session filled for id=" + courseSessionId + " is requested");

        int percent;

        CourseSession css  = entityManager.find(CourseSession.class, courseSessionId);
        Integer maxStudent = css.getMaxStudent();
        if(maxStudent == null){
            percent = 0;
        }else{
            ArrayList<Client> listClient = entityClientDao.getListClientRegisterCourseSession(courseSessionId);
            if(listClient.isEmpty()){
                percent = 0;
            }else{
                percent = listClient.size()*100 / maxStudent;
            }
        }

        return percent;
    }

    /**
     * Method to save a course session
     *
     * @param courseSession to save
     * @return saved course session
     */
    public CourseSession save(@NonNull CourseSession courseSession) {
        log.info("Course session : " + courseSession.toString() + " requested to be saved");

        entityManager.persist(courseSession);

        log.info("The course session : " + courseSession.toString() + " have been saved");

        return courseSession;
    }

    /**
     * Method to update a course session
     *
     * @param courseSession to update
     * @return updated course session
     */
    public CourseSession update(@NonNull CourseSession courseSession) {
        log.info("Course session : " + courseSession.toString() + " requested to be updated");

        entityManager.merge(courseSession);

        log.info("The course session : " + courseSession.toString() + " have been updated");

        return courseSession;

    }
}
