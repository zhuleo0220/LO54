package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.RedisCourseDao;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.tools.RedisUtil;
import org.apache.log4j.Logger;
import org.redisson.api.RKeys;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class RedisCourseDaoImpl implements RedisCourseDao {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(RedisCourseDaoImpl.class.getName());

    @Override
    public Course getCourseById(String courseId) throws NullPointerException {
        logger.info("A course with the id=" + courseId + " have been requested in Redis");

        // open a new redis connection
        RedisUtil util = new RedisUtil();
        RedissonClient client = util.getClient();
        RLiveObjectService service = client.getLiveObjectService();
        Course course = null;

        try {
            course = new Course(service.get(Course.class, courseId));
            logger.info("The course " + course.toString() + " have been find in Redis to solve the  course's request for the id=" + courseId);
        }catch(NullPointerException npt){
            logger.error("None course have been found in Redis with the id=" + courseId);
            throw new NullPointerException();
        }

        util.shutdown();

        // close the connection opened a bit earlier
        return course;
    }

    /**
     * Method to get all the course in the dataBase
     * @return List of all course
     */
    @Override
    public ArrayList<Course> getListCourse() {
        logger.info("The list of all course in Redis is requested");

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

        logger.info("A list of " + listCourse.size() + " have been found in Redis");
        return listCourse;
    }

    @Override
    public void save(Course course) throws IllegalArgumentException {
        logger.info("The course " + course.toString() + " will be saved in redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        try{
            serviceRedis.persist(course);
            logger.info("The course " + course.toString() + " have been saved in redis");
        }catch(IllegalArgumentException iarg){
            iarg.printStackTrace();
            logger.error("While saving the course " + course.toString() + ", an illegal argument exception occured");
            logger.error("The course " + course.toString() + " has not been saved in Redis");
            throw new IllegalArgumentException();
        }

        // close the connection opened a bit earlier
        rutil.shutdown();
    }

    @Override
    public void update(Course course) throws IllegalArgumentException {
        logger.info("The course " + course.toString() + " will be updated in redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        try{
            serviceRedis.merge(course);
            logger.info("The course " + course.toString() + " have been updated in redis");
        }catch(IllegalArgumentException iarg){
            logger.error("While saving the course " + course.toString() + ", an illegal argument exception occured");
            logger.error("The course " + course.toString() + " has not been updated in Redis");
            throw new IllegalArgumentException();
        }

        // close the connection opened a bit earlier
        rutil.shutdown();
    }

    @Override
    public void delete(Course course) throws IllegalArgumentException {
        logger.info("The course " + course.toString() + " will be deleted in redis");

        // open a new redis connection
        RedisUtil rutil = new RedisUtil();
        RedissonClient clientRedis = rutil.getClient();
        RLiveObjectService serviceRedis = clientRedis.getLiveObjectService();

        try{
            serviceRedis.delete(course);
            logger.info("The course " + course.toString() + " have been deleted in redis");
        }catch(IllegalArgumentException iarg){
            logger.error("While saving the course " + course.toString() + ", an illegal argument exception occured");
            logger.error("The course " + course.toString() + " has not been deleted in Redis");
            throw new IllegalArgumentException();
        }

        // close the connection opened a bit earlier
        rutil.shutdown();
    }
}
