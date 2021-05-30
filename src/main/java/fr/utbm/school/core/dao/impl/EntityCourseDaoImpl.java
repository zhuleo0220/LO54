/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.dao.impl;

import fr.utbm.school.core.dao.EntityCourseDao;
import fr.utbm.school.core.entity.Course;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
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
 * Implementation of the DAO (Data Access Object) of
 * {@link Course}
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Repository
@Transactional
public class EntityCourseDaoImpl implements EntityCourseDao {
    
    @PersistenceContext
    private EntityManager entityManager ;

    // Number of result per page
    public static final Integer RESULTPERPAGE = 10;

    /**
     * Get course by his id
     *
     * @param courseId of the course searched
     * @return course with the given courseId
     */
    public Course getCourseById(@NonNull String courseId){

        return entityManager.find(Course.class, courseId);

    }

    /**
     * Get a course by a word in his title
     *
     * @param keyword to search for in course's title
     * @return Course containing teh keyword in their title
     */
    public ArrayList<Course> getCourseByKeyword(String keyword){
        log.info("Searching for course containing the word " + keyword + " in their title");

        // If the word is null it will return all courses
        if(keyword == null){
            return getListCourse();
        }

        ArrayList<Course> listLocation = new ArrayList<Course>();

        // Create the query
        Query q = entityManager.createQuery("from Course crs where crs.title like :searchCriteria ORDER BY crs.code");
        q.setParameter("searchCriteria", "%" + keyword + "%");

        // Get the result of the query
        listLocation = (ArrayList<Course>) q.getResultList();

        return listLocation;
    }

    /**
     * Get all courses
     *
     * @return List of all course
     */
    public ArrayList<Course> getListCourse(){
        log.info("All course store in JavaDB using Hibernate requested");

        ArrayList<Course> listLocation = new ArrayList<Course>();

        // Create the query
        Query q = entityManager.createQuery("from Course crs ORDER BY crs.code");

        // Get the result of the query
        listLocation = (ArrayList<Course>) q.getResultList();
        return listLocation;
    }

    /**
     * Method to save a course
     *
     * @param course to save
     * @return the course deleted
     *
     * @throws SQLException Cann occur if the course id already exists
     */
    public Course save(@NonNull Course course) throws SQLException {
        log.trace("The course : " + course.toString() + " requested to be saved");
        
        entityManager.persist(course);

        return course;
    }

    /**
     * Method to update a course
     *
     * @param course to update
     * @return the course updated
     */
    public Course update(@NonNull Course course) {
        log.trace("The course : " + course.toString() + " requested to be updated");
        
        entityManager.merge(course);

        return course;

    }

    /**
     * Method to delete a course
     *
     * @param course to delete
     * @return the course deleted
     */
    @Override
    public Course delete(@NonNull Course course) {
        log.trace("The course : " + course.toString() + " requested to be deleted");
        
        entityManager.remove(course);

        return course;
    }

    /**
     * Method to get list of all word use
     *
     * @return List of all word in title
     */
    public ArrayList<String> getCourseKeyword(){
        log.info("A list of all word used in course title of course stored in Redis");

        // create the set of words used in title
        Set<String> setCourse = new HashSet<>();

        // for each course
        Iterator<Course> iteratorCourse = this.getListCourse().iterator();
        while(iteratorCourse.hasNext()){
            // Create set of words used in title
            String[] words = iteratorCourse.next().getTitle().split(" ");
            for(String word:words){
                // add all word to the set
                setCourse.add(word);
            }
        }

        // Convert the set to arrayList
        ArrayList<String> listCourse = new ArrayList<>(setCourse);

        log.info("A list of " + listCourse.size()  + " words used in course's title have been returned");
        return listCourse;
    }

    /**
     * Get the number of page needed to print all courses
     *
     * @return the number of pages needed for the pagination
     */
    public Integer getNbPageNeeded(){
        return ((int) Math.floor(this.getListCourse().size()/RESULTPERPAGE)) + 1;
    }

    /**
     * Get the number of page needed to print all courses  containing a giving keyword
     *
     * @param keyword keyword used in title
     * @return the number of pages needed for the pagination
     */
    public Integer getNbPageNeeded(String keyword){
        if(keyword == null){
            return this.getNbPageNeeded();
        }
        return ((int) Math.floor(this.getCourseByKeyword(keyword).size()/RESULTPERPAGE)) + 1;
    }

    /**
     * Get list of course for a giving page
     *
     * @param pageNumber the num of the page
     * @return List of course for the give page
     */
    public ArrayList<Course> getListCourse(@NonNull Integer pageNumber){
        log.info("All course store in JavaDB using Hibernate requested");

        ArrayList<Course> listLocation = new ArrayList<Course>();

        // Create the query
        Query q = entityManager.createQuery("from Course crs ORDER BY crs.code");
        q.setFirstResult((pageNumber - 1) * RESULTPERPAGE);
        q.setMaxResults(RESULTPERPAGE);

        // Get the query
        listLocation = (ArrayList<Course>) q.getResultList();
        return listLocation;
    }

    /**
     * Get list of course for a giving page and keyword
     *
     * @param keyword
     * @param pageNumber
     * @return
     */
    public ArrayList<Course> getCourseByKeyword(String keyword, @NonNull Integer pageNumber){
        log.info("Searching for course containing the word " + keyword + " in their title at the page " + pageNumber);

        if(keyword == null){
            return getListCourse(pageNumber);
        }

        ArrayList<Course> listLocation = new ArrayList<Course>();

        // Create the query
        Query q = entityManager.createQuery("from Course crs where crs.title like :searchCriteria ORDER BY crs.code");
        q.setParameter("searchCriteria", "%" + keyword + "%");

        q.setFirstResult((pageNumber - 1) * RESULTPERPAGE);
        q.setMaxResults(RESULTPERPAGE);

        listLocation = (ArrayList<Course>) q.getResultList();

        return listLocation;
    }

}
