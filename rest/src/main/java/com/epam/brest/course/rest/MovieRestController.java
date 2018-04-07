package com.epam.brest.course.rest;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import com.epam.brest.course.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MovieRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    private MovieService movieService;

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movies")
    public final Collection<MovieEarned> movies() {
        LOGGER.debug("REST movies()");
        return movieService.moviesEarned();
    }

    @GetMapping(value = "/movies/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Movie movieById(@PathVariable(value = "id") final int id) {
        LOGGER.debug("REST movieById()");
        return movieService.getMovieById(id);
    }

    @PostMapping(value = "/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public final Movie addMovie(@RequestBody final Movie movie) {
        LOGGER.debug("REST addMovie({})", movie);
        return movieService.addMovie(movie);
    }

    @PutMapping(value = "/movies")
    public final void updateMovie(@RequestBody final Movie movie) {
        LOGGER.debug("REST updateMovie({})", movie);
        movieService.updateMovie(movie);
    }

    @PutMapping(value = "/movies/{id}")
    public final void deleteMovie(@PathVariable(value = "id") final int id) {
        LOGGER.debug("REST deleteMovie({})", id);
        movieService.deleteMovie(id);
    }
}
