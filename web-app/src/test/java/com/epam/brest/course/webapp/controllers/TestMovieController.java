//package com.epam.brest.course.webapp.controllers;
//
//import com.epam.brest.course.service.MovieService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.easymock.EasyMock.reset;
//import static org.easymock.EasyMock.verify;
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
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(movieController)
//                .setMessageConverters(new MappingJackson2HttpMessageConverter())
//                .build();
//
//    }
//
//    @After
//    public void tearDown() {
//        verify(mockMovieRestClient);
//        reset(mockMovieRestClient);
//    }
//}
