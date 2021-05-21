package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.LocationService;
import fr.utbm.school.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/Location")
public class LocationController {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(LocationController.class.getName());

    @Autowired
    private LocationService locationService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String AddLocation(Location location, HttpServletResponse response) throws IOException {
        logger.trace("Controller to save a location have been called");

        locationService.saveLocation(location);

        response.sendRedirect("/Home/Homepage?success=La ville a ete ajoute");
        return "home";

    }

    @RequestMapping(value = "/addpage", method = RequestMethod.GET)
    public String LocationPage() {
        logger.trace("Controller to load the add location page have been called");

        return "addLocation";
    }


}
