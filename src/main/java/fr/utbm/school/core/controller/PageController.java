package fr.utbm.school.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping
@Controller
public class PageController {
    @RequestMapping(value = "/page/**/*.jsp")
    public String page(HttpServletRequest request) {
        String pattern = (String) request
                .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String searchTerm = new AntPathMatcher().extractPathWithinPattern(
                pattern, request.getServletPath());
        return searchTerm.substring(0, searchTerm.length() - 4);
    }
}
