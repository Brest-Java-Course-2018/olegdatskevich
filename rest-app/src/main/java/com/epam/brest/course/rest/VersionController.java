package com.epam.brest.course.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST version controller.
 */
@RestController
public class VersionController {

    /**
     * Constant version.
     */
    public static final String VERSION = "1.0";

    /**
     * Output version of app.
     * @return - version of application.
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public final String getVersion() {
        return VERSION;
    }
}
