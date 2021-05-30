package fr.utbm.school.core.controller;

import fr.utbm.school.core.service.ClientService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Controller
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    ClientService clientService;

    /**
     * Controller to login
     * @param email
     * @param session
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler
    @ResponseBody
    @PostMapping(value = "/doLogin.do")
    public String doLogin(@RequestParam("email") String email, HttpSession session, HttpServletResponse response) throws IOException {
        log.trace("Controller to login have been called");

        // If a client exists with this email
        if (!clientService.searchClientByEmail(email).isEmpty()){
            // add a cookie
            session.setAttribute("emailUser",email);
            Cookie c=new Cookie ("emailUser",email);
            c.setDomain("localhost");
            c.setPath("/");
            response.addCookie(c);
            log.info("logging with user "+ email);

            response.sendRedirect("/Home/Homepage?success=Vous etes connecte");
            return "home";

        }

        log.info("logging with user "+ email+" failed");

        response.sendRedirect("/Home/Homepage?failure=Compte inconnu");
        return "home";

    }

    /**
     * Cnotroller to logout
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/doLogout.do")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.trace("Controller to logout have been called");

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
