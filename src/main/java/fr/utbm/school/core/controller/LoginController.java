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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public String doLogin(@RequestParam("email") String email, HttpSession session, HttpServletRequest request)  {
System.out.println("1");
        if (!clientService.searchClientByEmail(email).isEmpty()){
            session.setAttribute("emailUser",email);
            logger.info("logging with user "+ email);
            return "home";

        }
        logger.info("logging with user "+ email+" failed");

        return "home";

    }

}
