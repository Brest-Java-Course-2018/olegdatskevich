package com.epam.brest.course.webapp.controllers;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dao.Session;
import com.epam.brest.course.service.MovieService;
import com.epam.brest.course.service.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class SessionController {

    /**
     * Logger for Session controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieService movieService;

    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "/sessions")
    public final String getSessions(final Model model) {
        LOGGER.debug("getSessionsWebApp({})", model);
        Collection<Movie> movies = movieService.getMovies();
        Collection<Session> sessions = sessionService.getSessions();
        model.addAttribute("movies", movies);
        model.addAttribute("sessions", sessions);
        return "sessions";
    }

    @GetMapping(value = "/session")
    public final String addSession(final Model model) {
        LOGGER.debug("getAddSession({})", model);
        Collection<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("session", new Session());
        model.addAttribute("isNew", true);
        return "session";
    }

    @PostMapping(value = "/session")
    public final String addSession(@Valid final Session session,
                                   final BindingResult result,
                                   final Model model) {
        LOGGER.debug("postAddSession({}, {})", session, result);
        if (result.hasErrors()) {
            Collection<Movie> movies = movieService.getMovies();
            model.addAttribute("movies", movies);
            model.addAttribute("isNew", true);
            return "session";
        } else {
            sessionService.addSession(session);
            return "redirect:/sessions";
        }
    }

    @GetMapping(value = "/session/{id}")
    public final String updateSession(@PathVariable final int id,
                                      final Model model) {
        LOGGER.debug("getUpdateSession({})", id);
        Session session = sessionService.getSessionById(id);
        Collection<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("session", session);
        model.addAttribute("isNew", false);
        return "session";
    }

    @PostMapping(value = "/session/{id}")
    public final String updateSession(@Valid final Session session,
                                      final BindingResult result,
                                      final Model model) {
        LOGGER.debug("postUpdateSession({}, {})", session, result);
        if (result.hasErrors()) {
            Collection<Movie> movies = movieService.getMovies();
            model.addAttribute("movies", movies);
            model.addAttribute("isNew", false);
            return "session";
        } else {
            sessionService.updateSession(session);
            return "redirect:/sessions";
        }
    }

    @GetMapping(value = "/session/{id}/delete")
    public final String deleteSession(@PathVariable final int id) {
        LOGGER.debug("deleteSession({})", id);
        sessionService.deleteSession(id);
        return "redirect:/sessions";
    }
}
