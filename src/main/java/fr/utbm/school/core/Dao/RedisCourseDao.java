package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RedisCourseDao {

    Course getCourseById(String courseId);

    ArrayList<Course> getListCourse();

    void save(Course course);

    void update(Course course);

    void delete(Course course);

}
