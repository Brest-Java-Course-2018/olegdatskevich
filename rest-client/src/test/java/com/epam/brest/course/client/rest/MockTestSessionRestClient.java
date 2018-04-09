package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.dao.Session;
import com.epam.brest.course.service.SessionService;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class MockTestSessionRestClient {

    @Autowired
    private SessionService mockSessionService;

    @Autowired
    private RestTemplate mockRestTemplate;

    private static final int SESSION_ID = 1;
    private static final Session SESSION_1 = new Session();
    private static final Session SESSION_2 = new Session();

    @BeforeClass
    public static void beforeClass() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SESSION_1.setSessionId(1);
        SESSION_1.setSessionDate(formatDate.parse("2018-05-01"));
        SESSION_1.setSessionTime("13:00:00");
        SESSION_1.setSessionCost(5);
        SESSION_1.setSessionSold(25);
        SESSION_1.setSessionActive(true);
        SESSION_1.setMovieId(1);
        SESSION_2.setSessionId(1);
        SESSION_2.setSessionDate(formatDate.parse("2018-05-02"));
        SESSION_2.setSessionTime("13:00:00");
        SESSION_2.setSessionCost(6);
        SESSION_2.setSessionSold(26);
        SESSION_2.setSessionActive(true);
        SESSION_2.setMovieId(2);
    }

    @After
    public void tearDown() {
        verify(mockRestTemplate);
        reset(mockRestTemplate);
    }

    @Test
    public void mockTestSessionsClient() {
        List<Session> sessions = Arrays.asList(SESSION_1, SESSION_2);
        ResponseEntity entity = new ResponseEntity<>(sessions, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);

        Collection<Session> results = mockSessionService.getSessions();
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void mockTestFilterByDateClient() {
        List<Session> sessionsByDate = Arrays.asList(SESSION_1, SESSION_2);
        ResponseEntity entity = new ResponseEntity<>(sessionsByDate, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject())).andReturn(entity);
        replay(mockRestTemplate);

        Collection<Session> results = mockSessionService.filterSessionByDate(
                SESSION_1.getSessionDate(), SESSION_2.getSessionDate());
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void mockTestSessionByIdClient() {
        ResponseEntity entity = new ResponseEntity<>(SESSION_1, HttpStatus.FOUND);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);

        Session result = mockSessionService.getSessionById(SESSION_1.getSessionId());
        assertNotNull(result);
        assertEquals(SESSION_1, result);
    }

    @Test
    public void mockTestAddSessionClient() {
        ResponseEntity entity = new ResponseEntity<>(SESSION_1, HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(anyString(), anyObject(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);

        Session result = mockSessionService.addSession(SESSION_1);
        assertNotNull(result);
        assertEquals(SESSION_1, result);
    }

    @Test
    public void mockTestUpdateSessionClient() {
        mockRestTemplate.put(anyString(), anyObject());
        expectLastCall();
        replay(mockRestTemplate);
        mockSessionService.updateSession(SESSION_1);
    }

    @Test
    public void mockTestDeleteSessionClient() {
        mockRestTemplate.put(anyString(), anyObject());
        expectLastCall();
        replay(mockRestTemplate);
        mockSessionService.deleteSession(SESSION_ID);
    }
}
