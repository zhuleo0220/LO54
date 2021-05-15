/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityCourseDao;
import fr.utbm.school.core.entity.Course;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Neil FARMER
 */
@Repository
@Transactional
public class EntityCourseDaoImpl implements EntityCourseDao {
    @PersistenceContext
    private EntityManager entityManager ;

    private static final Logger logger = Logger.getLogger(EntityCourseDao.class.getName());



    public Course getCourseById(String courseId){

        return entityManager.find(Course.class, courseId);

    }

    public ArrayList<Course> getCourseByKeyword(String keyword){
        logger.info("Searching for course containing the word " + keyword + " in their title");

        if(keyword == null){
            return getListCourse();
        }

        ArrayList<Course> listLocation = new ArrayList<Course>();

        Query q = entityManager.createQuery("from Course crs where crs.title like :searchCriteria");
        q.setParameter("searchCriteria", "%" + keyword + "%");
        listLocation = (ArrayList<Course>) q.getResultList();

        return listLocation;
    }

    public ArrayList<Course> getListCourse(){

        logger.trace("All course requested");
        return this.getDBListCourse();
    }

    public ArrayList<Course> getDBListCourse(){
        logger.info("All course store in JavaDB using Hibernate requested");
        ArrayList<Course> listLocation = new ArrayList<Course>();
        Query q = entityManager.createQuery("from Course");
        listLocation = (ArrayList<Course>) q.getResultList();
        return listLocation;
    }

    public void save(Course course) throws SQLException {
        logger.trace("The course : " + course.toString() + " requested to be saved");
        entityManager.persist(course);


    }

    public void update(Course course) {
        logger.trace("The course : " + course.toString() + " requested to be updated");

        entityManager.merge(course);

    }

}
