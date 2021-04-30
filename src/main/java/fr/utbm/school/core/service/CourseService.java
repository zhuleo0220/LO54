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
 * @author neil
 */
public interface CourseService {
    public Course searchCourseById(String idCourse);

    public ArrayList<Course> getListCourse();

    public ArrayList<Course> getCourseByKeyword(String keyword);

    public void saveCourse(Course course) throws SQLException;

    public void updateCourse(Course course);
}
