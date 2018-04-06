package com.epam.brest.course.service;

import com.epam.brest.course.dao.MovieDao;
import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of MovieService from service-api.
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieDao movieDao;

    public final void setMovieDao(final MovieDao movieDao)
            throws DataAccessException {
        this.movieDao = movieDao;
    }

    @Override
    public final Collection<Movie> getMovies() throws DataAccessException {
        Collection<Movie> movies = movieDao.getMovies();
        LOGGER.debug("getMovies({})", movies);
        return movies;
    }

    @Override
    public final Movie getMovieById(final int movieId)
            throws DataAccessException {
        Movie movie = movieDao.getMovieById(movieId);
        LOGGER.debug("getMovieById({})", movie);
        return movie;
    }

    @Override
    public final Collection<MovieEarned> moviesEarned()
            throws DataAccessException {
        Collection<MovieEarned> moviesEarn = movieDao.moviesEarned();
        LOGGER.debug("moviesEarned({})");
        return moviesEarn;
    }

    @Override
    public final Movie addMovie(final Movie movie) throws DataAccessException {
        Movie addedMovie = movieDao.addMovie(movie);
        LOGGER.debug("addMovie({})", movie);
        return addedMovie;
    }

    @Override
    public final void updateMovie(final Movie movie)
            throws DataAccessException {
        LOGGER.debug("updateMovie({})", movie);
        movieDao.updateMovie(movie);
    }

    @Override
    public final void deleteMovie(final int movieId)
            throws DataAccessException {
        LOGGER.debug("deleteMovie({})", movieId);
        movieDao.deleteMovie(movieId);

    }
}
