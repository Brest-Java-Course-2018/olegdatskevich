package com.epam.brest.cource.service;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;

import java.util.Collection;

public interface MovieService {

    Collection<Movie> serviceGetMovies();

    Movie serviceGetMovieById(final int movieId);

    Collection<MovieEarned> serviceMoviesEarned();

    Movie serviceAddMovie(final Movie movie);

    void serviceUpdateMovie(final Movie movie);

    void serviceDeleteMovie(final int movieId);
}
