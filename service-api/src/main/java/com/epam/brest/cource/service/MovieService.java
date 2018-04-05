package com.epam.brest.cource.service;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;

import java.util.Collection;

public interface MovieService {

    Collection<Movie> getMovies();

    Movie getMovieById(final int movieId);

    Collection<MovieEarned> moviesEarned();

    Movie addMovie(final Movie movie);

    void updateMovie(final Movie movie);

    void deleteMovie(final int movieId);
}
