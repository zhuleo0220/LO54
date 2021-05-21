/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityCourseDao;
import fr.utbm.school.core.entity.Course;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CachePut;
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
 * @author Neil Farmer/Ruiqing Zhu
 */
@Repository
@Transactional
public class EntityCourseDaoImpl implements EntityCourseDao {
    @PersistenceContext
    private EntityManager entityManager ;

    private static final Logger logger = Logger.getLogger(EntityCourseDao.class.getName());

    /**
     *
     * @param courseId
     * @return
     */
    public Course getCourseById(String courseId){

        return entityManager.find(Course.class, courseId);

    }

    /**
     *
     * @param keyword
     * @return
     */
    public ArrayList<Course> getCourseByKeyword(String keyword){
        logger.info("Searching for course containing the word " + keyword + " in their title");

        if(keyword == null){
            return getListCourse();
        }

        ArrayList<Course> listLocation = new ArrayList<Course>();

        Query q = entityManager.createQuery("from Course crs where crs.title like :searchCriteria ORDER BY crs.code");
        q.setParameter("searchCriteria", "%" + keyword + "%");
        listLocation = (ArrayList<Course>) q.getResultList();

        return listLocation;
    }

    /**
     *
     * @return
     */
    public ArrayList<Course> getListCourse(){
        logger.info("All course store in JavaDB using Hibernate requested");
        ArrayList<Course> listLocation = new ArrayList<Course>();
        Query q = entityManager.createQuery("from Course crs ORDER BY crs.code");
        listLocation = (ArrayList<Course>) q.getResultList();
        return listLocation;
    }

    /**
     *
     * @param course
     * @throws SQLException
     */
    public Course save(Course course) throws SQLException {
        logger.trace("The course : " + course.toString() + " requested to be saved");

        assert course != null : "Null object can't be merged";
        entityManager.persist(course);

        return course;
    }

    /**
     *
     * @param course
     */
    public Course update(Course course) {
        logger.trace("The course : " + course.toString() + " requested to be updated");

        assert course != null : "Null object can't be merged";
        entityManager.merge(course);

        return course;

    }

    @Override
    public Course delete(Course course) {
        logger.trace("The course : " + course.toString() + " requested to be deleted");

        assert course != null : "Null object can't be deleted";
        entityManager.remove(course);

        return course;
    }

    /**
     * Method to get list of all word use
     * @return List of all word in title
     */
    public ArrayList<String> getCourseKeyword(){
        logger.info("A list of all word used in course title of course stored in Redis");

        // create the list to store course
        Set<String> setCourse = new HashSet<>();

        Iterator<Course> iteratorCourse = this.getListCourse().iterator();
        while(iteratorCourse.hasNext()){
            String[] words = iteratorCourse.next().getTitle().split(" ");
            for(String word:words){
                setCourse.add(word);
            }
        }

        ArrayList<String> listCourse = new ArrayList<>(setCourse);

        logger.info("A list of " + listCourse.size()  + " words used in course's title have been returned");
        return listCourse;
    }

}
