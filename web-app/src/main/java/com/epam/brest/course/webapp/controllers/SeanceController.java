package com.epam.brest.course.webapp.controllers;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dao.Seance;
import com.epam.brest.course.model.dto.MovieEarned;
import com.epam.brest.course.service.MovieService;
import com.epam.brest.course.service.SeanceService;
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

/**
 * Seance Controller for web-app.
 */
@Controller
public class SeanceController {

    /**
     * Logger for Seance controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Service of movie
     */
    @Autowired
    private MovieService movieService;

    /**
     * Service of seance
     */
    @Autowired
    private SeanceService seanceService;

    /**
     * Get mapping of
     * @param model
     * @return
     */
    @GetMapping(value = "/seances")
    public final String getSeances(final Model model) {
        LOGGER.debug("getSeancesWebApp({})", model);
        Collection<MovieEarned> movies = movieService.moviesEarned();
        Collection<Seance> seances = seanceService.getSeances();
        model.addAttribute("movies", movies);
        model.addAttribute("seances", seances);
        return "seances";
    }

    /*@PostMapping(value = "/")
    public final String filterSeanceByDate() {

    }*/

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/seance")
    public final String addSeance(final Model model) {
        LOGGER.debug("getAddSeance({})", model);
        Collection<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("seance", new Seance());
        model.addAttribute("isNew", true);
        return "seance";
    }

    /**
     *
     * @param seance
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "/seance")
    public final String addSeance(@Valid final Seance seance,
                                   final BindingResult result,
                                   final Model model) {
        LOGGER.debug("postAddSeance({}, {})", seance, result);
        if (result.hasErrors()) {
            Collection<Movie> movies = movieService.getMovies();
            model.addAttribute("movies", movies);
            model.addAttribute("seance", seance);
            model.addAttribute("isNew", true);
            return "seance";
        } else {
            seanceService.addSeance(seance);
            return "redirect:/seances";
        }
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/seance/{id}")
    public final String updateSeance(@PathVariable final int id,
                                      final Model model) {
        LOGGER.debug("getUpdateSeance({})", id);
        Seance seance = seanceService.getSeanceById(id);
        Collection<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("seance", seance);
        model.addAttribute("isNew", false);
        return "seance";
    }

    /**
     *
     * @param seance
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "/seance/{id}")
    public final String updateSeance(@Valid final Seance seance,
                                      final BindingResult result,
                                      final Model model) {
        LOGGER.debug("postUpdateSeance({}, {})", seance, result);
        if (result.hasErrors()) {
            Collection<Movie> movies = movieService.getMovies();
            model.addAttribute("seance", seance);
            model.addAttribute("movies", movies);
            model.addAttribute("isNew", false);
            return "seance";
        } else {
            seanceService.updateSeance(seance);
            return "redirect:/seances";
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/seance/{id}/delete")
    public final String deleteSeance(@PathVariable final int id) {
        LOGGER.debug("deleteSeance({})", id);
        seanceService.deleteSeance(id);
        return "redirect:/seances";
    }
}
