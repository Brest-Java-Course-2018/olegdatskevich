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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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
    public final String getSeances(final Model model) throws Exception {
        LOGGER.debug("getSeancesWebApp({})", model);
        Collection<MovieEarned> movies = movieService.moviesEarned();
        Collection<Seance> seances = seanceService.getSeances();
        model.addAttribute("movies", movies);
        model.addAttribute("seances", seances);
        return "seances";
    }

    /**
     *
     * @param fromDate
     * @param toDate
     * @param model
     * @return - path.
     * @throws ParseException
     */
    @GetMapping(value = "/seances/{fromDate}/{toDate}")
    public final String filterSeanceByDate(@PathVariable final String fromDate,
                                           @PathVariable final String toDate,
                                           final Model model) throws ParseException {
        LOGGER.debug("filterSeanceByDateWebApp({} - {})", fromDate, toDate);
        SimpleDateFormat formatDate
                = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date startDate = formatDate.parse(fromDate);
        Date endDate = formatDate.parse(toDate);
        Collection<MovieEarned> movies = movieService.moviesEarned();
        Collection<Seance> seances
                = seanceService.filterSeanceByDate(startDate, endDate);
        model.addAttribute("movies", movies);
        model.addAttribute("seances", seances);
        return "seances";
    }

    /**
     *
     * @param model
     * @return - path.
     */
    @GetMapping(value = "/seance")
    public final String addSeance(final Model model) throws Exception {
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
     * @return - path
     */
    @PostMapping(value = "/seance")
    public final String addSeance(@Valid final Seance seance,
                                   final BindingResult result,
                                   final Model model) throws Exception {
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
     * Get-update controller for seance.
     * @param id - seance's ID for output.
     * @param model - data for HTML-page.
     * @return - path.
     */
    @GetMapping(value = "/seance/{id}")
    public final String updateSeance(@PathVariable final int id,
                                      final Model model) throws Exception {
        LOGGER.debug("getUpdateSeance({})", id);
        Seance seance = seanceService.getSeanceById(id);
        Collection<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("seance", seance);
        model.addAttribute("isNew", false);
        return "seance";
    }

    /**
     * Post-update controller for seance.
     * @param seance - seance for update.
     * @param result - validation result.
     * @param model - data for HTML-page.
     * @return - path.
     */
    @PostMapping(value = "/seance/{id}")
    public final String updateSeance(@Valid final Seance seance,
                                      final BindingResult result,
                                      final Model model) throws Exception {
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
     * Delete controller for seance.
     * @param id - seance's ID for delete.
     * @return path.
     */
    @GetMapping(value = "/seance/{id}/delete")
    public final String deleteSeance(@PathVariable final int id)
            throws Exception {
        LOGGER.debug("deleteSeance({})", id);
        seanceService.deleteSeance(id);
        return "redirect:/seances";
    }
}
