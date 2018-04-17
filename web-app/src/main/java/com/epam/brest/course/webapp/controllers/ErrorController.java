package com.epam.brest.course.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController extends RuntimeException {

    /**
     *
     * @param exc
     * @param model
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final String exceptionHandler(final Exception exc,
                                         final Model model) {
        model.addAttribute("errorMessage", exc.toString());
        return "error";
    }
}
