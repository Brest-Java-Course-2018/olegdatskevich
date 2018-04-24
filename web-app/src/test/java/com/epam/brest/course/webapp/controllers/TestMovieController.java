package com.epam.brest.course.webapp.controllers;

import com.epam.brest.course.model.dao.Movie;
import com.epam.brest.course.model.dto.MovieEarned;
import com.epam.brest.course.model.dto.MoviesTitles;
import com.epam.brest.course.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-webapp-test.xml"})
public class TestMovieController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MovieController movieController;

    @Autowired
    private MovieService mockMovieService;

    private MockMvc mockMvc;

    private static final int MOVIE_ID = 1;
    private static final MovieEarned MOVIE_EARNED_1 = new MovieEarned();
    private static final MovieEarned MOVIE_EARNED_2 = new MovieEarned();
    private static final MoviesTitles MOVIES_TITLES_1 = new MoviesTitles();
    private static final MoviesTitles MOVIES_TITLES_2 = new MoviesTitles();
    private static final Movie MOVIE_1 = new Movie();
    private static final Movie MOVIE_2 = new Movie();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders
                .standaloneSetup(movieController)
                .setViewResolvers(viewResolver)
                .build();

        MOVIE_EARNED_1.setMovieId(1);
        MOVIE_EARNED_1.setMovieName("REST_CLIENT_MOVIE_1");
        MOVIE_EARNED_1.setEarned(100501);
        MOVIE_EARNED_2.setMovieId(2);
        MOVIE_EARNED_2.setMovieName("REST_CLIENT_MOVIE_2");
        MOVIE_EARNED_2.setEarned(100502);

        MOVIE_1.setMovieId(1);
        MOVIE_1.setMovieName("REST_CLIENT_MOVIE_1");
        MOVIE_1.setMovieDescription("REST_CLIENT_MOVIE_DESCR_1");
        MOVIE_1.setMovieActive(true);
        MOVIE_2.setMovieId(2);
        MOVIE_2.setMovieName("REST_CLIENT_MOVIE_2");
        MOVIE_2.setMovieDescription("REST_CLIENT_MOVIE_DESCR_2");
        MOVIE_2.setMovieActive(true);

        reset(mockMovieService);
    }

    @Test
    public void testGetMovies() throws Exception {
        LOGGER.debug("testGetMovies()");
        expect(mockMovieService.moviesEarned()).andReturn(Arrays.asList(MOVIE_EARNED_1));
        replay(mockMovieService);

        mockMvc.perform(
                get("/movies")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("movies"))
                .andExpect(view().name("movies"));
        verify(mockMovieService);
    }

    @Test
    public void testGetAddMovie() throws Exception {
        LOGGER.debug("testGetAddMovie()");

        mockMvc.perform(
                get("/movie")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attributeExists("isNew"))
                .andExpect(model().attribute("movie", new Movie()))
                .andExpect(model().attribute("isNew", true))
                .andExpect(view().name("movie"));
    }

    @Test
    public void testAddMovie() throws Exception {
        LOGGER.debug("testAddMovie()");

    }

    @Test
    public void testGetUpdateMovie() throws Exception {
        LOGGER.debug("testGetUpdateMovie()");
        expect(mockMovieService.getMovieById(MOVIE_ID)).andReturn(MOVIE_1);
        replay(mockMovieService);

        mockMvc.perform(
                get("/movie/{id}", MOVIE_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attributeExists("isNew"))
                .andExpect(model().attribute("movie", MOVIE_1))
                .andExpect(model().attribute("isNew", false))
                .andExpect(view().name("movie"));
        verify(mockMovieService);
    }
}
