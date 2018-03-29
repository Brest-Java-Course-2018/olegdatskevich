package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collection;

public class MovieDaoImpl implements MovieDao {

    private static final Logger LOGGER
            = LogManager.getLogger(MovieDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String MOVIE_ID = "movieId";
    private static final String MOVIE_NAME = "movieName";
    private static final String MOVIE_DESCR = "movieDescription";
    private static final String MOVIE_IS_ACTIVE = "movieIsActive";

    @Value("${movie.select}")
    private String movieSelect;
    @Value("${movie.selectById}")
    private String movieSelectById;
    @Value("${movie.check}")
    private String checkMovie;
    @Value("${movie.calculate}")
    private String movieCalcalulateEarn;
    @Value("${movie.insert}")
    private String insert;
    @Value("${movie.update}")
    private String update;
    @Value("${movie.delete}")
    private String delete;

    public void setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Collection<Movie> getMovies() {
        LOGGER.debug("getMovies()");
        Collection<Movie> movies = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(movieSelect,
                        BeanPropertyRowMapper.newInstance(Movie.class));
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) {
        return null;
    }

    @Override
    public Collection<MovieEarned> movieEarned() {
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
