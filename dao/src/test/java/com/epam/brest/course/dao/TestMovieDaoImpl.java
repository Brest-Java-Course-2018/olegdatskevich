package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:testdb-spring.xml",
        "classpath:dao.xml",
        "classpath:dao-test.xml"})
@Rollback
@Transactional
public class TestMovieDaoImpl {

    private static final Logger LOGGER
            = LogManager.getLogger(MovieDaoImpl.class);

    @Autowired
    private MovieDao movieDao;

    @Test
    public void testGetMovies() {
        Collection<Movie> movies = movieDao.getMovies();
        System.out.println(movies.size());
        LOGGER.debug("testGetMovies({})", movies);
        assertFalse(movies.isEmpty());
    }

    @Test
    public void testGetMovieById() {
        Movie movie = movieDao.getMovieById(2);
        LOGGER.debug("testGetMovieById({})", movie);
        assertNotNull(movie);
        assertTrue(movie.getMovieId().equals(2));
        assertTrue(movie.getMovieName().equals("Terminator"));
        assertTrue(movie.getMovieDescription().equals("Arni"));
        assertTrue(movie.isMovieActive());
    }

    @Test
    public void testMoviesEarned() {
        Collection<MovieEarned> movies = movieDao.moviesEarned();
        LOGGER.debug("testMoviesEarned({})", movies);
        System.out.println(movies.size());
        assertFalse(movies.isEmpty());
    }

    @Test
    public void addDepartment() {
        Collection<Movie> movies = movieDao.getMovies();
        int sizeBeforeAdd = movies.size();
        Movie newMovie = movieDao.addMovie(new Movie(
                "testAddMovie",
                "testAddDescr",
                true));
        int addedMovieId = newMovie.getMovieId();
        //assertNotNull(addedMovieId);
        assertEquals(newMovie.getMovieName(),
                movieDao.getMovieById(addedMovieId).getMovieName());
        assertEquals(newMovie.getMovieDescription(),
                movieDao.getMovieById(addedMovieId).getMovieDescription());
        assertTrue(movieDao.getMovieById(addedMovieId).isMovieActive());
        //assertTrue(newMovie.getMovieName().equals(testDepartment.getDepartmentName()));
        //assertTrue(newDepartment.getDepartmentDescription().equals(testDepartment.getDepartmentDescription()));
        assertTrue((sizeBeforeAdd + 1) == movieDao.getMovies().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSameMovie() {
        Movie testDepartment = new Movie(
                "testAddSameName",
                "TestAddSameDescr",
                true);
        movieDao.addMovie(testDepartment);
        movieDao.addMovie(testDepartment);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void addSameMovieWithRule() {
        Movie testMovie = new Movie(
                "TestAddSameNameRule", "TestAddSameDescrRule", true);
        movieDao.addMovie(testMovie);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Movie with the same name already exists in DB.");
        movieDao.addMovie(testMovie);
    }

    @Test
    public void testUpdateMovie() {
        Movie newMovie = movieDao.addMovie(new Movie(
                "testUpdateMovie",
                "testUpdateDescr",
                true));
        newMovie.setMovieName("NEW" + newMovie.getMovieName());
        newMovie.setMovieDescription("NEW" + newMovie.getMovieDescription());
        newMovie.setMovieActive(!newMovie.isMovieActive());
        LOGGER.debug("testUpdateMovie({})", newMovie);
        int updatedMovieId = newMovie.getMovieId();
        movieDao.updateMovie(newMovie);
        assertEquals(newMovie.getMovieName(),
                movieDao.getMovieById(updatedMovieId).getMovieName());
        assertEquals(newMovie.getMovieDescription(),
                movieDao.getMovieById(updatedMovieId).getMovieDescription());
        assertFalse(movieDao.getMovieById(updatedMovieId).isMovieActive());
    }

    @Test
    public void testDeleteMovie() {
        Movie movie = new Movie("testDeleteMovie",
                "testDeleteDescr", true);
        movie = movieDao.addMovie(movie);
        int delMovieId = movie.getMovieId();
        LOGGER.debug("testDeleteMovie({})", movie);
        Collection<Movie> movies = movieDao.getMovies();
        int sizeBeforeDelete = movies.size();
        movieDao.deleteMovie(movie.getMovieId());
        assertTrue(sizeBeforeDelete - 1 == movieDao.getMovies().size());
        assertEquals(movie.getMovieName(),
                movieDao.getMovieById(delMovieId).getMovieName());
    }
}
