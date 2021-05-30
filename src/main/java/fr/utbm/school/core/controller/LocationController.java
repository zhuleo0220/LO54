package fr.utbm.school.core.controller;

import com.codahale.metrics.Timer;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.LocationService;
import fr.utbm.school.core.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Controller
@RequestMapping("/Location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * Controller to add a location
     * @param location
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler
    @PostMapping(value = "/add")
    @ResponseBody
    public String addLocation(@Valid Location location, HttpServletResponse response) throws IOException {
        log.trace("Controller to save a location have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

        // Time the transaction
        Timer.Context contextTransaction = timer.time();

        locationService.saveLocation(location);

        // end the timer
        long timeTransaction = contextTransaction.stop();

        // end of the timer of teh controller
        long timeController = contextController.stop();
        // print it into the log
        log.trace("Metric : time spent in addLocation(nano-seconds) : " + timeController + " | time spent in saveLocation(nano-seconds) : " + timeTransaction);

        // return statement
        response.sendRedirect("/Home/Homepage?success=La ville a ete ajoute");
        return "home";

    }

    /**
     * Controller to load the page to add a location
     * @return
     */
    @GetMapping(value = "/addpage")
    public String locationPage() {
        log.trace("Controller to load the add location page have been called");

        return "addLocation";
    }


}
