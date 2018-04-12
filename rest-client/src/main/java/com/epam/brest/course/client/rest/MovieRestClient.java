package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import com.epam.brest.course.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * Rest client for MOVIE.
 */
public class MovieRestClient implements MovieService {

    /**
     * Logger for MovieRestClient.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * URL for MOVIES.
     */
    private String url;
    /**
     * For interaction with REST module.
     */
    private RestTemplate restTemplate;

    /**
     * Constructor of MovieRestClient
     * @param url - url request.
     * @param restTemplate - REST Template
     */
    public MovieRestClient(final String url, final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Collection<Movie> getMovies()
            throws ServerDataAccessException  {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Movie> movies = (List<Movie>)responseEntity.getBody();
        LOGGER.debug("REST-client getMovies({})", responseEntity);
        return movies;
    }

    @Override
    public final Movie getMovieById(final int movieId)
            throws ServerDataAccessException {
        ResponseEntity<Movie> responseEntity = restTemplate
                .getForEntity(url + "/" + movieId, Movie.class);
        Movie movie = responseEntity.getBody();
        LOGGER.debug("REST-client getMovieById({})", responseEntity);
        return movie;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Collection<MovieEarned> moviesEarned()
            throws ServerDataAccessException {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<MovieEarned> moviesEarned
                = (List<MovieEarned>)responseEntity.getBody();
        LOGGER.debug("REST-client moviesEarned({})", responseEntity);
        return moviesEarned;
    }

    @Override
    public final Movie addMovie(final Movie movie)
            throws ServerDataAccessException {
        ResponseEntity<Movie> responseEntity
                = restTemplate.postForEntity(url, movie, Movie.class);
        Movie result = (Movie)responseEntity.getBody();
        LOGGER.debug("REST-client addMovie({})", responseEntity);
        return result;
    }

    @Override
    public final  void updateMovie(final Movie movie)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client updateMovie({})", movie);
        restTemplate.put(url, movie);
    }

    @Override
    public final void deleteMovie(final int movieId)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client deleteMovie({})", movieId);
        restTemplate.put(url + "/" + movieId, movieId);
        //restTemplate.put(url + "/{}", movieId);
    }
}
