package fr.utbm.school.core.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/Home")
public class HomeController {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/Homepage", method = RequestMethod.GET)
    public String HomePage() {
        logger.trace("Controller to load the home page have been called");

        return "home";
    }

}
