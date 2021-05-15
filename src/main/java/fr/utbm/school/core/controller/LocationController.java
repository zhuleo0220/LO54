package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.LocationService;
import fr.utbm.school.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Location")
public class LocationController {


    @Autowired
    private LocationService locationService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String AddLocation(Location location) {
        locationService.saveLocation(location);

        return "home";

    }

    @RequestMapping(value = "/addpage", method = RequestMethod.GET)
    public String LocationPage() {

        return "addLocation";
    }


}
