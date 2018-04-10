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

public class MovieRestClient implements MovieService {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url;
    private RestTemplate restTemplate;

    public MovieRestClient(final String url, final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Movie> getMovies() throws ServerDataAccessException  {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Movie> movies = (List<Movie>)responseEntity.getBody();
        LOGGER.debug("REST-client getMovies({})", responseEntity);
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) throws ServerDataAccessException {
        ResponseEntity<Movie> responseEntity = restTemplate
                .getForEntity(url + "/" + movieId, Movie.class);
        Movie movie = responseEntity.getBody();
        LOGGER.debug("REST-client getMovieById({})", responseEntity);
        return movie;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<MovieEarned> moviesEarned()
            throws ServerDataAccessException {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<MovieEarned> moviesEarned
                = (List<MovieEarned>)responseEntity.getBody();
        LOGGER.debug("REST-client moviesEarned({})", responseEntity);
        return moviesEarned;
    }

    @Override
    public Movie addMovie(Movie movie) throws ServerDataAccessException {
        ResponseEntity<Movie> responseEntity
                = restTemplate.postForEntity(url, movie, Movie.class);
        Movie result = (Movie)responseEntity.getBody();
        LOGGER.debug("REST-client addMovie({})", responseEntity);
        return result;
    }

    @Override
    public void updateMovie(Movie movie) throws ServerDataAccessException {
        LOGGER.debug("REST-client updateMovie({})", movie);
        restTemplate.put(url, movie);
    }

    @Override
    public void deleteMovie(int movieId) throws ServerDataAccessException {
        LOGGER.debug("REST-client deleteMovie({})", movieId);
        restTemplate.put(url + "/" + movieId, movieId);
        //restTemplate.put(url + "/{}", movieId);
    }
}
