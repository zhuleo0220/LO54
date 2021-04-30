/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityCourseDao;
import fr.utbm.school.core.entity.Course;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
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



    public Course getCourseById(String courseId){

        return entityManager.find(Course.class, courseId);

    }

    public ArrayList<Course> getCourseByKeyword(String keyword){
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
        return this.getDBListCourse();
    }

    public ArrayList<Course> getDBListCourse(){
        ArrayList<Course> listLocation = new ArrayList<Course>();
        Query q = entityManager.createQuery("from Course");
        listLocation = (ArrayList<Course>) q.getResultList();
        return listLocation;
    }

    public void save(Course course) throws SQLException {
        entityManager.persist(course);


    }

    public void update(Course course) {
        entityManager.merge(course);

    }


}
