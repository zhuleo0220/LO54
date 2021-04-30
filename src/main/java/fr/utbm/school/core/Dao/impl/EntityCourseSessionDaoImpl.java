/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityClientDao;
import fr.utbm.school.core.Dao.EntityCourseSessionDao;
import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.CourseSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


/**
 *
 * @author neil
 */
@Repository
@Transactional
public class EntityCourseSessionDaoImpl implements EntityCourseSessionDao {
    @PersistenceContext
    private EntityManager entityManager ;

    @Autowired
    private EntityClientDao entityClientDao;

    public CourseSession getCourseSessionById(Long courseSessionId){
        return entityManager.find(CourseSession.class, courseSessionId);
    }

    public ArrayList<CourseSession> getListCourseSession(){
        ArrayList<CourseSession> listCourseSession = new ArrayList<CourseSession>();
        Query q = entityManager.createQuery("from CourseSession");
        listCourseSession = (ArrayList<CourseSession>) q.getResultList();
        return listCourseSession;
    }

    public ArrayList<CourseSession> searchCourseSessionByCourseId(String idCourse){

	Query query = entityManager.createQuery("FROM CourseSession CSS WHERE CSS.course.code = :courseId");
        query.setParameter("courseId", idCourse);

	ArrayList<CourseSession> courseSessionList = new ArrayList<CourseSession>();
        courseSessionList = (ArrayList<CourseSession>) query.getResultList();

        return courseSessionList;
    }

    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId){


        String sqlQuery = "FROM CourseSession CSS";
        if(date != null && locationId != null){
            sqlQuery += " WHERE CSS.location.id = :locationId and YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(date != null){
            sqlQuery += " WHERE YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(locationId != null){
            sqlQuery += " WHERE CSS.location.id = :locationId";
        }

	Query query = entityManager.createQuery(sqlQuery);

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

    public ArrayList<CourseSession> searchCourseSessionByParameter(Timestamp date, Long locationId, String courseCode){

        if(courseCode == null){
            return this.searchCourseSessionByParameter(date, locationId);
        }


        String sqlQuery = "FROM CourseSession CSS WHERE css.course.code = :courseCode";
        if(date != null && locationId != null){
            sqlQuery += " AND CSS.location.id = :locationId AND YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(date != null){
            sqlQuery += " AND YEAR(CSS.startDate) = :dateYear AND MONTH(CSS.startDate) = :dateMonth AND DAY(CSS.startDate) = :dateDay";
        }else if(locationId != null){
            sqlQuery += " AND CSS.location.id = :locationId";
        }

	Query query = entityManager.createQuery(sqlQuery);
        query.setParameter("courseCode", courseCode);

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

    public int getPercentStudent(Long courseSessionId){
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

    public void save(CourseSession courseSession) throws CourseSessionException {
        if(courseSession.getStartDate().compareTo(courseSession.getEndDate()) > 0){
            throw new CourseSessionException("The start date is after the end date of the session");
        }else if(courseSession.getStartDate().compareTo(Timestamp.from(Instant.now())) < 0){
            throw new CourseSessionException("The start date is before the date");
        }


        entityManager.persist(courseSession);
    }

    public void update(CourseSession courseSession) {
        entityManager.merge(courseSession);
    }
}
