package com.epam.brest.course.webapp.controllers;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import com.epam.brest.course.service.MovieService;
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
public class MovieController {

    /**
     * Logger for Movie controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private MovieService movieService;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/movies")
    public final String getMovies(final Model model) {
        LOGGER.debug("getMovies({})", model);
        Collection<MovieEarned> movies = movieService.moviesEarned();
        model.addAttribute("movies", movies);
        return "movies";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/movie")
    public final String getAddMovie(final Model model) {
        LOGGER.debug("GetAddMovie({})", model);
        model.addAttribute("movie", new Movie());
        model.addAttribute("isNew", true);
        return "movie";

    }

    /**
     *
     * @param movie
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "/movie")
    public final String addMovie(@Valid final Movie movie,
                                    final BindingResult result,
                                    final Model model) {
        LOGGER.debug("PostAddMovie({}, {})", movie, result);
        if (result.hasErrors()) {
            model.addAttribute("movie", movie);
            model.addAttribute("isNew", true);
            return "movie";
        } else {
            movieService.addMovie(movie);
            return "redirect:/movies";
        }
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/movie/{id}")
    public final String getUpdateMovie(
            @PathVariable final int id, final Model model) {
        LOGGER.debug("GetUpdateMovie({}, {})", id, model);
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isNew", false);
        return "movie";
    }

    /**
     *
     * @param movie
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "/movie/{id}")
    public final String updateMovie(@Valid final Movie movie,
                                    final BindingResult result,
                                    final Model model) {
        LOGGER.debug("PostUpdateMovie({}, {})", movie, result);
        if (result.hasErrors()) {
            model.addAttribute("movie", movie);
            model.addAttribute("isNew", false);
            return "movie";
        } else {
            movieService.updateMovie(movie);
            return "redirect:/movies";
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/movie/{id}/delete")
    public final String deleteMovie(@PathVariable final int id)
            throws Exception {
        LOGGER.debug("deleteMovie({})", id);
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}
