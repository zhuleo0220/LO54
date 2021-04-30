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
 * @author Neil FARMER
 */
@Transactional
public interface EntityCourseDao {


    public Course getCourseById(String courseId);

    public ArrayList<Course> getCourseByKeyword(String keyword);

    public ArrayList<Course> getListCourse();

    public ArrayList<Course> getDBListCourse();

    public void save(Course course) throws SQLException ;

    public void update(Course course);

}
