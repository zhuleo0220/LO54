package fr.utbm.school.core.dao.impl;

import fr.utbm.school.core.dao.RedisCourseDao;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.tools.RedisUtil;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.redisson.api.*;
import org.redisson.api.condition.Conditions;
import org.redisson.api.listener.MessageListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Log4j
public class RedisCourseDaoImpl implements RedisCourseDao {

    /**
     * Method to get a course stored in redis by his id
     *
     * @param courseId to find in Redis
     * @return The attached course
     *
     * @throws NullPointerException
     */
    @Override
    public Course getCourseById(@NonNull String courseId) throws NullPointerException {
        log.info("A course with the id=" + courseId + " have been requested in Redis");

        // open a new redis connection
        RedisUtil util = new RedisUtil();
        RedissonClient client = util.getClient();
        RLiveObjectService service = client.getLiveObjectService();
        Course course = null;

        try {
            course = new Course(service.get(Course.class, courseId));
            log.info("The course " + course.toString() + " have been find in Redis to solve the  course's request for the id=" + courseId);
        }catch(NullPointerException npt){
            log.error("None course have been found in Redis with the id=" + courseId);
            throw new NullPointerException();
        }

        // shutdown Redis connection
        util.shutdown();

        // close the connection opened a bit earlier
        return course;
    }

    /**
     * Method to get a course stored in Redis by his title
     *
     * @param title of the course
     * @return list of course with this title
     *
     * @throws NullPointerException
     */
    @Override
    public Collection<Course> getCourseByTitle(@NonNull String title) throws NullPointerException {
        log.info("A course with the title=" + title + " have been requested in Redis");

        // open a new redis connection
        RedisUtil util = new RedisUtil();
        RedissonClient client = util.getClient();
        RLiveObjectService service = client.getLiveObjectService();
        Collection<Course> course = null;

        try {
            course = service.find(Course.class, Conditions.eq("title", title));
            log.info("The course " + course.toString() + " have been find in Redis to solve the  course's request for the id=" + title);
        }catch(NullPointerException npt){
            log.error("None course have been found in Redis with the id=" + title);
            throw new NullPointerException();
        }

        // close the connection opened a bit earlier
        return course;
    }

    /**
     * Method to get all the course in the dataBase
     *
     * @return List of all course
     */
    @Override
    public ArrayList<Course> getListCourse() {
        log.info("The list of all course in Redis is requested");

        // create the list to store course
        ArrayList<Course> listCourse = new ArrayList<>();

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RKeys keys = clientRedis.getKeys();

        RLiveObjectService service = clientRedis.getLiveObjectService();

        Iterable<String> iterableId = service.findIds(Course.class);
        Iterator iteratorId = iterableId.iterator();
        while(iteratorId.hasNext()){
            listCourse.add(new Course(service.get(Course.class, iteratorId.next())));
        }

        // close the connection opened a bit earlier
        rutil.shutdown();

        log.info("A list of " + listCourse.size() + " have been found in Redis");
        return listCourse;
    }

    /**
     * Method yo save a course in redis
     *
     * @param course to save
     *
     * @throws IllegalArgumentException
     */
    @Override
    public void save(@NonNull Course course) throws IllegalArgumentException {
        log.info("The course " + course.toString() + " will be saved in redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        try{
            serviceRedis.persist(course);
            log.info("The course " + course.toString() + " have been saved in redis");
        }catch(IllegalArgumentException iarg){
            iarg.printStackTrace();
            log.error("While saving the course " + course.toString() + ", an illegal argument exception occured");
            log.error("The course " + course.toString() + " has not been saved in Redis");
            throw new IllegalArgumentException();
        }

        // close the connection opened a bit earlier
        rutil.shutdown();
    }

    /**
     * Method to update a course in redis
     *
     * @param course to update
     *
     * @throws IllegalArgumentException
     */
    @Override
    public void update(@NonNull Course course) throws IllegalArgumentException {
        log.info("The course " + course.toString() + " will be updated in redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        try{
            serviceRedis.merge(course);
            log.info("The course " + course.toString() + " have been updated in redis");
        }catch(IllegalArgumentException iarg){
            log.error("While saving the course " + course.toString() + ", an illegal argument exception occured");
            log.error("The course " + course.toString() + " has not been updated in Redis");
            throw new IllegalArgumentException();
        }

        // close the connection opened a bit earlier
        rutil.shutdown();
    }

    /**
     * Method to delete a course in redis
     *
     * @param course to delete
     *
     * @throws IllegalArgumentException
     */
    @Override
    public void delete(@NonNull Course course) throws IllegalArgumentException {
        log.info("The course " + course.toString() + " will be deleted in redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        try{
            serviceRedis.delete(course);
            log.info("The course " + course.toString() + " have been deleted in redis");
        }catch(IllegalArgumentException iarg){
            log.error("While deleting the course " + course.toString() + ", an illegal argument exception occured");
            log.error("The course " + course.toString() + " has not been deleted in Redis");
            throw new IllegalArgumentException();
        }

        // close the connection opened a bit earlier
        rutil.shutdown();
    }

    /**
     * Method to publish a course on a giving topic
     * Warning : The course must not have been saved before
     * -> This method save object before publish them
     *
     * @param topic to publish the course
     * @param course to publish
     *
     * @return Number of subscriber who received the object
     */
    @Override
    public Long publish(@NonNull String topic, @NonNull Course course){
        log.info("The course " + course.toString() + " will be published in topic " + topic + " with Redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        RTopic redisTopic = clientRedis.getTopic(topic);
        long clientsReceivedMessage = redisTopic.publish(serviceRedis.persist(course));

        log.info("The course " + course.toString() + " have been published in topic " + topic + " with Redis. " +
                clientsReceivedMessage + " subscriber(s) received the notification");

        return clientsReceivedMessage;
    }

    /**
     * method to subscribe on a topic
     *
     * @param topic to publish the course
     * @param listener to publish
     *
     * @return the topic
     */
    public RTopic subscribe(@NonNull String topic, @NonNull MessageListener<? extends Course> listener){
        log.info("New subscriber to the topic " + topic + " with Redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil(false);
        RedissonClient clientRedis = rutil.getClient();

        RTopic redisTopic = clientRedis.getTopic(topic);
        redisTopic.addListener(Course.class, listener);

        return redisTopic;
    }

}
