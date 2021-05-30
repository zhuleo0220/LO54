package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.LocationService;
import fr.utbm.school.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/Test")
public class TestController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private LocationService locationService;


    /**
     *
     * @throws SQLException
     */
    @ExceptionHandler
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    @ResponseBody
    public void locationTest() throws SQLException {
        Location location=new Location();
        location.setCity("City");
        locationService.saveLocation(location);

    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @ExceptionHandler
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    @ResponseBody
    public List<Course> redisTest() throws SQLException {

        return courseService.getListCourse();
    }

    /**
     *
     * @param model
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/3", method = RequestMethod.GET)
    public String jspTest(Model model) throws SQLException {
        return "home";
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @GetMapping(value = "/4")
    public String navBarTest() throws SQLException {
        return "navBar";

    }


}
