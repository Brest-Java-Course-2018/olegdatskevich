package com.epam.brest.course.web_app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC controller.
 */
@Controller
public class HomeController {

    /**
     * Logger for Home controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Redirect to departments.html.
     */
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        LOGGER.debug("defaultPageRedirect");
        return "redirect:departments";
    }
}
