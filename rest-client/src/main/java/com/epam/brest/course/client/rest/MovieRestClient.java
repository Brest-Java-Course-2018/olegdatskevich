package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import com.epam.brest.course.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class MovieRestClient implements MovieService {

    private String url;
    private RestTemplate restTemplate;

    public MovieRestClient(final String url, final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Movie> getMovies() {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Movie> movies = (List<Movie>)responseEntity.getBody();
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) {
        return null;
    }

    @Override
    public Collection<MovieEarned> moviesEarned() {
        return null;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(int movieId) {

    }
}
