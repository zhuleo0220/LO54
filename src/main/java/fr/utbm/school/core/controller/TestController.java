package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.LocationService;
import fr.utbm.school.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Test")
public class TestController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private LocationService locationService;


    @RequestMapping(value = "/1", method = RequestMethod.GET)
    @ResponseBody
    public void LocationTest() throws SQLException {
        Location location=new Location();
        location.setCity("City");
        locationService.saveLocation(location);

    }

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    @ResponseBody
    public List<Course> RedisTest() throws SQLException {

        return courseService.getListCourse();
    }

    @RequestMapping(value = "/3", method = RequestMethod.GET)
    public String JspTest(Model model) throws SQLException {
        return "home";
    }
    @RequestMapping(value = "/4", method = RequestMethod.GET)
    public String NavBarTest() throws SQLException {
        return "navBar";

    }


}
