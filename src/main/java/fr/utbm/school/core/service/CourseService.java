/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface CourseService {
    Course searchCourseById(String idCourse);

    ArrayList<Course> getListCourse();

    ArrayList<Course> getCourseByKeyword(String keyword);

    Course saveCourse(Course course) throws SQLException;

    Course updateCourse(Course course);

    ArrayList<String> getCourseKeyword();
}
