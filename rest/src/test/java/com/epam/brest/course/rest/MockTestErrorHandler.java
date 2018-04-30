package com.epam.brest.course.rest;

import com.epam.brest.course.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-error-test.xml",
        "classpath:testdb-spring.xml", "classpath:dao.xml"})
@Rollback
public class MockTestErrorHandler {

    @Autowired
    private MovieRestController movieRestController;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RestErrorHandler restErrorHandler;

    private MockMvc mockMvc;

    private static final int MOVIE_ID = 50;

    @Before
    public void testSetUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieRestController)
                .setControllerAdvice(restErrorHandler)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void handleException() throws Exception {

        mockMvc.perform(get("/movies/{id}", MOVIE_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"Exception:Incorrect result size: expected 1, actual 0\""));
    }
}
