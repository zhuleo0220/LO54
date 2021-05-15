package fr.utbm.school.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping("/Home")
public class HomeController {

    @RequestMapping(value = "/Homepage", method = RequestMethod.GET)
    public String HomePage() {

        return "home";
    }

}
