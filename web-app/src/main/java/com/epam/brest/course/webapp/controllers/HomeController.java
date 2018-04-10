package com.epam.brest.course.webapp.controllers;

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
     * Redirect to movies.html.
     * @return - template name.
     */
    @GetMapping(value = "/")
    public final String defaultPageRedirect() {
        LOGGER.debug("defaultPageRedirect()");
        return "redirect:movies";
    }
}
