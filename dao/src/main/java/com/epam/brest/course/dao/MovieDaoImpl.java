package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

public class MovieDaoImpl implements MovieDao {

    private static final Logger LOGGER
            = LogManager.getLogger(MovieDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String MOVIE_ID = "movieId";
    private static final String MOVIE_NAME = "movieName";
    private static final String MOVIE_DESCR = "movieDescription";
    private static final String MOVIE_ACTIVE = "movieActive";

    @Value("${movie.select}")
    private String moviesSelect;
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
                .query(moviesSelect,
                        BeanPropertyRowMapper.newInstance(Movie.class));
        return movies;
    }

    @Override
    public Movie getMovieById(final int movieId) {
        LOGGER.debug("getMovieById({})", movieId);
        SqlParameterSource namedParameters
                = new MapSqlParameterSource(MOVIE_ID, movieId);
        Movie movie = namedParameterJdbcTemplate.queryForObject(
                movieSelectById,
                namedParameters,
                BeanPropertyRowMapper.newInstance(Movie.class));
        return movie;
    }

    @Override
    public Collection<MovieEarned> moviesEarned() {
        LOGGER.debug("moviesEarned()");
        Collection<MovieEarned> movies = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(movieCalcalulateEarn,
                        BeanPropertyRowMapper.newInstance(MovieEarned.class));
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        LOGGER.debug("addMovie({})", movie);
        MapSqlParameterSource namedParameter
                = new MapSqlParameterSource(MOVIE_NAME, movie.getMovieName());
        Integer result = namedParameterJdbcTemplate
                .queryForObject(checkMovie, namedParameter, Integer.class);
        if (result == 0) {
            namedParameter = new MapSqlParameterSource();
            namedParameter.addValue(MOVIE_NAME, movie.getMovieName());
            namedParameter.addValue(MOVIE_DESCR, movie.getMovieDescription());
            namedParameter.addValue(MOVIE_ACTIVE, movie.isMovieActive());
            KeyHolder generatedKey = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insert, namedParameter, generatedKey);
            movie.setMovieId(generatedKey.getKey().intValue());
        } else {
            throw new IllegalArgumentException(
                    "Movie with the same name already exists in DB.");
        }
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) {
        LOGGER.debug("updateMovie({})", movie);
        SqlParameterSource namedParameter
                = new BeanPropertySqlParameterSource(movie);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    @Override
    public void deleteMovie(int movieId) {
        LOGGER.debug("deleteMovie({})", movieId);
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, movieId);
    }
}
