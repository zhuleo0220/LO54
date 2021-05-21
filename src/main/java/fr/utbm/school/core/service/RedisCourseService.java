package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RedisCourseService {

    Course getCourseById(String courseId);

    ArrayList<Course> getListCourse();

    void save(Course course);

    void update(Course course);

    void delete(Course course);

}
