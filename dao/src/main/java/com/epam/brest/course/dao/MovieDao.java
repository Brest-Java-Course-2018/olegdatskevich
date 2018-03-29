package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;

import java.util.Collection;

public interface MovieDao {

    Collection<Movie> getMovies();

    Movie getMovieById(final int movieId);

    Collection<MovieEarned> movieEarned();

    Movie addMovie(final Movie movie);

    void updateMovie(final Movie movie);

    void deleteMovie(final int movieId);
}
