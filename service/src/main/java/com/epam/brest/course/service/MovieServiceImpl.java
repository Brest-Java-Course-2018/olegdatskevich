package com.epam.brest.course.service;

import com.epam.brest.cource.service.MovieService;
import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;

import java.util.Collection;

/**
 * Implementation of MovieService from service-api.
 */
public class MovieServiceImpl implements MovieService {

    @Override
    public final Collection<Movie> getMovies() {
        return null;
    }

    @Override
    public final Movie getMovieById(final int movieId) {
        return null;
    }

    @Override
    public final Collection<MovieEarned> moviesEarned() {
        return null;
    }

    @Override
    public final Movie addMovie(final Movie movie) {
        return null;
    }

    @Override
    public final void updateMovie(final Movie movie) {

    }

    @Override
    public final void deleteMovie(final int movieId) {

    }
}
