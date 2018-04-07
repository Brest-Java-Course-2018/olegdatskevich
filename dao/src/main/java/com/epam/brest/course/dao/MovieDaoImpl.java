package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

/**
 * Implementation of DAO layer for movies.
 */
public class MovieDaoImpl implements MovieDao {

    /**
     * Logger for MovieDaoImpl.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MovieDaoImpl.class);

    /**
     * NamedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Column name in movie table DB.
     */
    private static final String MOVIE_ID = "movieId";

    /**
     * SQL query for select all movies.
     */
    @Value("${movie.select}")
    private String moviesSelect;
    /**
     * SQL query for select movie by id.
     */
    @Value("${movie.selectById}")
    private String movieSelectById;
    /**
     * SQL query for calculate earn.
     */
    @Value("${movie.calculate}")
    private String movieCalcalulateEarn;
    /**
     * SQL query for insert movie.
     */
    @Value("${movie.insert}")
    private String insert;
    /**
     * SQL query for update movie.
     */
    @Value("${movie.update}")
    private String update;
    /**
     * SQL query for delete movie.
     */
    @Value("${movie.delete}")
    private String delete;

    /**
     * Setter for NamedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate - namedParam.
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Collection<Movie> getMovies() {
        LOGGER.debug("getMovies()");
        Collection<Movie> movies = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(moviesSelect,
                        BeanPropertyRowMapper.newInstance(Movie.class));
        return movies;
    }

    @Override
    public final Movie getMovieById(final int movieId) {
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
    public final Collection<MovieEarned> moviesEarned() {
        LOGGER.debug("moviesEarned()");
        Collection<MovieEarned> movies = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(movieCalcalulateEarn,
                        BeanPropertyRowMapper.newInstance(MovieEarned.class));
        return movies;
    }

    @Override
    public final Movie addMovie(final Movie movie) throws DataAccessException {
        LOGGER.debug("addMovie({})", movie);
        SqlParameterSource namedParameters
                = new BeanPropertySqlParameterSource(movie);
        KeyHolder generatedKey = new GeneratedKeyHolder();
        namedParameterJdbcTemplate
                .update(insert, namedParameters, generatedKey);
        movie.setMovieId(generatedKey.getKey().intValue());
        return movie;
    }

    @Override
    public final void updateMovie(final Movie movie) {
        LOGGER.debug("updateMovie({})", movie);
        SqlParameterSource namedParameter
                = new BeanPropertySqlParameterSource(movie);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    @Override
    public final void deleteMovie(final int movieId) {
        LOGGER.debug("deleteMovie({})", movieId);
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, movieId);
    }
}
