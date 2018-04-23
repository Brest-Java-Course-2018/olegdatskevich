//package com.epam.brest.course.webapp.controllers;
//
//import com.epam.brest.course.model.dao.Movie;
//import com.epam.brest.course.model.dto.MovieEarned;
//import com.epam.brest.course.service.MovieService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import static org.easymock.EasyMock.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-webapp-test.xml"})
//public class TestMovieController {
//
//    private static final Logger LOGGER = LogManager.getLogger();
//
//    @Autowired
//    private MovieController movieController;
//
//    @Autowired
//    private MovieService mockMovieRestClient;
//
//    private MockMvc mockMvc;
//
//    private static final int MOVIE_ID = 1;
//    private static final MovieEarned MOVIE_EARNED_1 = new MovieEarned();
//    private static final MovieEarned MOVIE_EARNED_2 = new MovieEarned();
//    private static final Movie MOVIE_1 = new Movie();
//    private static final Movie MOVIE_2 = new Movie();
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(movieController)
//                .setMessageConverters(new MappingJackson2HttpMessageConverter())
//                .build();
//
//        MOVIE_EARNED_1.setMovieId(1);
//        MOVIE_EARNED_1.setMovieName("REST_CLIENT_MOVIE_1");
//        MOVIE_EARNED_1.setEarned(100501);
//        MOVIE_EARNED_2.setMovieId(2);
//        MOVIE_EARNED_2.setMovieName("REST_CLIENT_MOVIE_2");
//        MOVIE_EARNED_2.setEarned(100502);
//
//        MOVIE_1.setMovieId(1);
//        MOVIE_1.setMovieName("REST_CLIENT_MOVIE_1");
//        MOVIE_1.setMovieDescription("REST_CLIENT_MOVIE_DESCR_1");
//        MOVIE_1.setMovieActive(true);
//        MOVIE_2.setMovieId(2);
//        MOVIE_2.setMovieName("REST_CLIENT_MOVIE_2");
//        MOVIE_2.setMovieDescription("REST_CLIENT_MOVIE_DESCR_2");
//        MOVIE_2.setMovieActive(true);
//    }
//
//    @After
//    public void tearDown() {
//
//
//    }
//
//    @Test
//    public void testGetMovies() throws Exception {
//        LOGGER.debug("testGetMoviesController({})");
//        Collection<MovieEarned> moviesEarned = Arrays.asList(MOVIE_EARNED_1, MOVIE_EARNED_2);
//        expect(mockMovieRestClient.moviesEarned()).andReturn(moviesEarned);
//        replay(mockMovieRestClient);
//
//        mockMvc.perform(get("/movies"))
//                .andExpect(view().name("movies"))
//                .andExpect(model().attribute("movies", moviesEarned));
//        verify(mockMovieRestClient);
//        reset(mockMovieRestClient);
//    }
//}
