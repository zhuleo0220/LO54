package fr.utbm.school.core.controller;

import fr.utbm.school.core.Dao.EntityCourseDao;
import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.service.ClientService;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.LocationService;
import fr.utbm.school.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpCookie;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    ClientService clientService;

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Client> Test()  {
        return clientService.searchClientByEmail("Eric.dampiere978@gmail.com");

    }


    @RequestMapping(value = "/doLogin.do", method = RequestMethod.POST)
    public String doLogin(@RequestParam("email") String email, HttpSession session, HttpServletResponse response) throws IOException {
        logger.trace("Controller to login have been called");

        if (!clientService.searchClientByEmail(email).isEmpty()){
            session.setAttribute("emailUser",email);
            Cookie c=new Cookie ("emailUser",email);
            c.setDomain("localhost");
            c.setPath("/");
            response.addCookie(c);
            logger.info("logging with user "+ email);

            response.sendRedirect("/Home/Homepage?success=Vous etes connecte");
            return "home";

        }

        logger.info("logging with user "+ email+" failed");

        response.sendRedirect("/Home/Homepage?failure=Compte inconnu");
        return "home";

    }

    @RequestMapping(value = "/doLogout.do", method = RequestMethod.POST)
    public String doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.trace("Controller to logout have been called");

        Cookie cookie = new Cookie("emailUser", null);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        //add cookie to response
        response.addCookie(cookie);

        response.sendRedirect("/Home/Homepage?success=Vous avez ete deconnecte");
        return "home";
    }
}
