package fr.utbm.school.core.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Controller
@RequestMapping("/Home")
public class HomeController {

    /**
     * Home page load controller
     * @return
     */
    @GetMapping(value = "/Homepage")
    public String homePage() {
        log.trace("Controller to load the home page have been called");

        return "home";
    }

}
