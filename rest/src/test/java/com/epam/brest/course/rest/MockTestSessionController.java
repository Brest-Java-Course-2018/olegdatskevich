package com.epam.brest.course.rest;

import com.epam.brest.course.model.dao.Session;
import com.epam.brest.course.service.SessionService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class MockTestSessionController {

    @Autowired
    private SessionRestController sessionRestController;

    @Autowired
    private SessionService mockSessionService;

    private MockMvc mockMvc;

    private static final int SESSION_ID = 1;
    private static final Session SESSION = new Session();
    private static final String DATE = "2018-05-01";
    private static final String FROM_DATE = "2018-05-01";
    private static final String TO_DATE = "2018-05-02";

    @BeforeClass
    public static void beforeClass() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);
        SESSION.setSessionId(SESSION_ID);
        SESSION.setSessionDate(date);
        SESSION.setSessionTime("13:00:00");
        SESSION.setSessionCost(5);
        SESSION.setSessionSold(25);
        SESSION.setSessionActive(true);
        SESSION.setMovieId(2);
    }

    @Before
    public void setUP() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(sessionRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(mockSessionService);
        reset(mockSessionService);
    }

    @Test
    public void mockTestSessions() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);

        expect(mockSessionService.getSessions()).andReturn(Arrays.asList(SESSION));
        replay(mockSessionService);

        mockMvc.perform(
                get("/sessions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]sessionId", Matchers.is(SESSION_ID)))
                .andExpect(jsonPath("$[0]sessionDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("$[0]sessionTime", Matchers.is("13:00:00")))
                .andExpect(jsonPath("$[0]sessionCost", Matchers.is(5)))
                .andExpect(jsonPath("$[0]sessionSold", Matchers.is(25)))
                .andExpect(jsonPath("$[0]sessionActive", Matchers.is(true)))
                .andExpect(jsonPath("$[0]movieId", Matchers.is(2)));
    }

    @Test
    public void mockTestSessionById() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);

        expect(mockSessionService.getSessionById(SESSION_ID)).andReturn(SESSION);
        replay(mockSessionService);

        mockMvc.perform(
                get("/sessions/" + SESSION_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("sessionId", Matchers.is(SESSION_ID)))
                .andExpect(jsonPath("sessionDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("sessionTime", Matchers.is("13:00:00")))
                .andExpect(jsonPath("sessionCost", Matchers.is(5)))
                .andExpect(jsonPath("sessionSold", Matchers.is(25)))
                .andExpect(jsonPath("sessionActive", Matchers.is(true)))
                .andExpect(jsonPath("movieId", Matchers.is(2)));
    }

    @Test
    public void mockTestAddSession() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);

        expect(mockSessionService.addSession(anyObject())).andReturn(SESSION);
        replay(mockSessionService);

        mockMvc.perform(
                post("/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sessionId\":1,\"sessionDate\":1525122000000,\"sessionTime\":\"13:00:00\",\"sessionCost\":5,\"sessionSold\":25,\"sessionActive\":true,\"movieId\":2}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("sessionId", Matchers.is(SESSION_ID)))
                .andExpect(jsonPath("sessionDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("sessionTime", Matchers.is("13:00:00")))
                .andExpect(jsonPath("sessionCost", Matchers.is(5)))
                .andExpect(jsonPath("sessionSold", Matchers.is(25)))
                .andExpect(jsonPath("sessionActive", Matchers.is(true)))
                .andExpect(jsonPath("movieId", Matchers.is(2)));
    }

    @Test
    public void mockTestUpdateSession() throws Exception {
        mockSessionService.updateSession(anyObject());
        expectLastCall();
        replay(mockSessionService);

        mockMvc.perform(
                put("/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sessionId\":1,\"sessionDate\":1525122000000,\"sessionTime\":\"13:00:00\",\"sessionCost\":5,\"sessionSold\":25,\"sessionActive\":true,\"movieId\":2}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockTestDeleteMovie() throws Exception {
        mockSessionService.deleteSession(SESSION_ID);
        expectLastCall();
        replay(mockSessionService);

        mockMvc.perform(
                put("/sessions/" + SESSION_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockTestFilterByDate() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);
        Date fromDate = formatDate.parse(FROM_DATE);
        Date toDate = formatDate.parse(TO_DATE);

        expect(mockSessionService.filterSessionByDate(fromDate, toDate))
                .andReturn(Arrays.asList(SESSION));
        replay(mockSessionService);

        mockMvc.perform(
                get("/sessions/" + FROM_DATE + "/" + TO_DATE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]sessionId", Matchers.is(SESSION_ID)))
                .andExpect(jsonPath("$[0]sessionDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("$[0]sessionTime", Matchers.is("13:00:00")))
                .andExpect(jsonPath("$[0]sessionCost", Matchers.is(5)))
                .andExpect(jsonPath("$[0]sessionSold", Matchers.is(25)))
                .andExpect(jsonPath("$[0]sessionActive", Matchers.is(true)))
                .andExpect(jsonPath("$[0]movieId", Matchers.is(2)));
    }
}
