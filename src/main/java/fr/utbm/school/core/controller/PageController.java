package fr.utbm.school.core.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@RequestMapping
@Controller
public class PageController {

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/page/**/*.jsp")
    public String page(HttpServletRequest request) {
        String pattern = (String) request
                .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String searchTerm = new AntPathMatcher().extractPathWithinPattern(
                pattern, request.getServletPath());
        return searchTerm.substring(0, searchTerm.length() - 4);
    }
}
