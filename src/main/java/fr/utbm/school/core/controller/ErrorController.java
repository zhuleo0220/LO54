package fr.utbm.school.core.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Controller
@RequestMapping("/lo54")
public class ErrorController {

    /**
     * Error load page controller
     * @return
     */
    @GetMapping(value = "/error")
    public String errorPage() {
        log.trace("Controller to load the error page have been called");

        return "error";
    }

}
