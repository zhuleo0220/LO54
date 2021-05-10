package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public static void main(String[] args) {
        Course course=new Course();
        course.setCode("TXt4");
        course.setTitle("title");

    }

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    @ResponseBody
    public void test() throws SQLException {
        Course course=new Course();
        course.setCode("TXt4");
        course.setTitle("title");
        courseService.saveCourse(course);
    }

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    @ResponseBody
    public List<Course> RedisTest() throws SQLException {

         return courseService.getListCourse();
    }

}
