package fr.utbm.school.core.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/lo54")
public class ErrorController {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(ErrorController.class.getName());

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String LocationPage() {
        logger.trace("Controller to load the error page have been called");

        return "error";
    }

}
