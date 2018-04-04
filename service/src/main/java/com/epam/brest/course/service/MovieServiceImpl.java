package com.epam.brest.course.service;

import com.epam.brest.cource.service.MovieService;
import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;

import java.util.Collection;

public class MovieServiceImpl implements MovieService {

    @Override
    public Collection<Movie> serviceGetMovies() {
        return null;
    }

    @Override
    public Movie serviceGetMovieById(final int movieId) {
        return null;
    }

    @Override
    public Collection<MovieEarned> serviceMoviesEarned() {
        return null;
    }

    @Override
    public Movie serviceAddMovie(final Movie movie) {
        return null;
    }

    @Override
    public void serviceUpdateMovie(final Movie movie) {

    }

    @Override
    public void serviceDeleteMovie(final int movieId) {

    }
}
