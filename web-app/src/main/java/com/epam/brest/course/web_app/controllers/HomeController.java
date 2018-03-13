package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC controller.
 */
@Controller
public class HomeController {
    /**
     * Redirect to departments.html.
     */
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:departments";
    }
}
