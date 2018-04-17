/*
package com.epam.brest.course.webapp.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-webapp-test.xml"})
public class TestHomeController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private HomeController homeController;

    private MockMvc mockMvc;

    private static String reference;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(homeController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        reference = "testhome";
    }

    @Test
    public void testHomeController() throws Exception {
        LOGGER.debug("testHomeController()");
        mockMvc.perform(get("/" + reference))
               .andExpect(status().isOk())
               .andExpect(view().name("redirect:movies"));
    }
}
*/
