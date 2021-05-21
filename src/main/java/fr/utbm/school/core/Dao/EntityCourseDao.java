/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.Course;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Transactional
public interface EntityCourseDao {


    Course getCourseById(String courseId);

    ArrayList<Course> getCourseByKeyword(String keyword);

    ArrayList<Course> getListCourse();

    Course save(Course course) throws SQLException ;

    Course update(Course course);

    Course delete(Course course);

    ArrayList<String> getCourseKeyword();

}
