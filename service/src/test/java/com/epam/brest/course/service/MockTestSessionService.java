package com.epam.brest.course.service;

import com.epam.brest.course.dao.SessionDao;
import com.epam.brest.course.model.dao.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class MockTestSessionService {

    @Autowired
    private SessionDao mockSessionDao;

    @Autowired
    private SessionService sessionService;

    private static final int SESSION_ID = 1;
    private static final Session SESSION = new Session();

    @Before
    public void setUp() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse("2018-05-01");
        SESSION.setSessionDate(date);
        SESSION.setSessionTime("09:00:00");
        SESSION.setSessionCost(5);
        SESSION.setSessionSold(25);
        SESSION.setSessionActive(true);
        SESSION.setMovieId(1);
    }

    @After
    public void tearDown() {
        verify(mockSessionDao);
        reset(mockSessionDao);
    }

    @Test
    public void mockTestGetSession() {
        Collection<Session> sessions = Arrays.asList(SESSION);
        expect(mockSessionDao.getSessions()).andReturn(sessions);
        replay(mockSessionDao);
        sessionService.getSessions();
    }

    @Test
    public void mockTestGetSessionById() {
        expect(mockSessionDao.getSessionById(SESSION_ID)).andReturn(new Session());
        replay(mockSessionDao);
        sessionService.getSessionById(SESSION_ID);
    }

    @Test
    public void mockTestAddSession() {
        expect(mockSessionDao.addSession(SESSION)).andReturn(new Session());
        replay(mockSessionDao);
        sessionService.addSession(SESSION);
    }

    @Test
    public void mockTestUpdateSession() {
        mockSessionDao.updateSession(SESSION);
        expectLastCall();
        replay(mockSessionDao);
        sessionService.updateSession(SESSION);
    }

    @Test
    public void mockTestDeleteSession() {
        mockSessionDao.deleteSession(SESSION_ID);
        expectLastCall();
        replay(mockSessionDao);
        sessionService.deleteSession(SESSION_ID);
    }

    @Test
    public void mockTestFilterSessionByDates() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatDate.parse("2018-04-02");
        Date toDate = formatDate.parse("2018-04-03");
        Collection<Session> sessions = Arrays.asList(SESSION);
        expect(mockSessionDao.filterSessionByDate(fromDate, toDate)).andReturn(sessions);
        replay(mockSessionDao);
        sessionService.filterSessionByDate(fromDate, toDate);
    }
}
