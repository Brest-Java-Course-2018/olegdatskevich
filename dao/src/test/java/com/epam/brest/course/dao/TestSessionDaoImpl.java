package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:testdb-spring.xml",
        "classpath:dao.xml",
        "classpath:dao-test.xml"})
@Rollback
@Transactional
public class TestSessionDaoImpl {
    private static final Logger LOGGER
            = LogManager.getLogger(SessionDaoImpl.class);

    private static final String DATE = "2018-05-01";
    private static final String TIME = "09:00:00";
    private static final int COST = 5;
    private static final int SOLD = 25;
    private static final boolean ACTIVE = true;
    private static final int MOVIE_ID = 1;

    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final String FROM_DATE = "2018-04-02";
    private static final String TO_DATE = "2018-04-03";

    @Autowired
    private SessionDao sessionDao;

    @Test
    public void testGetSessions() {
        Collection<Session> sessions = sessionDao.getSessions();
        LOGGER.debug("testGetSessions({})", sessions);
        assertFalse(sessions.isEmpty());
    }

    @Test
    public void testGetSessionById() throws ParseException {
        Date date = formatDate.parse(DATE);
        Session session = sessionDao.addSession(
                new Session(date, TIME, COST, SOLD, ACTIVE, MOVIE_ID));
        int addedSession = session.getSessionId();
        Session receivedSession = sessionDao.getSessionById(addedSession);
        LOGGER.debug("testAddSession({}, {})", session, receivedSession);
        assertTrue(session.getSessionDate().equals(receivedSession.getSessionDate()));
        assertTrue(session.getSessionTime().equals(receivedSession.getSessionTime()));
        assertTrue(session.getSessionCost() == receivedSession.getSessionCost());
    }

    @Test
    public void testAddSession() throws ParseException {
        Date date = formatDate.parse(DATE);
        Collection<Session> sessions = sessionDao.getSessions();
        int sizeBefore = sessions.size();
        Session session = new Session(date, TIME, COST, SOLD, ACTIVE, MOVIE_ID);
        Session addedSession = sessionDao.addSession(session);
        LOGGER.debug("testAddSession({}, {})", session, addedSession);
        assertNotNull(addedSession);
        assertTrue(session.getSessionDate().equals(addedSession.getSessionDate()));
        assertTrue((sizeBefore + 1) == sessionDao.getSessions().size());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testAddSameSession() throws ParseException {
        Date date = formatDate.parse(DATE);
        Session session = sessionDao.addSession(
                new Session(date, TIME, COST, SOLD, ACTIVE, MOVIE_ID));
        sessionDao.addSession(session);
        sessionDao.addSession(session);
    }

    @Test
    public void testUpdateSession() throws ParseException {
        Date date = formatDate.parse(DATE);
        Session newSession = sessionDao.addSession(
                new Session(date, TIME, COST, SOLD, ACTIVE, MOVIE_ID));
        newSession.setSessionCost(2);
        newSession.setMovieId(3);
        LOGGER.debug("testUpdateSession({})", newSession);
        int updatedSessionId = newSession.getSessionId();
        sessionDao.updateSession(newSession);
        assertEquals(newSession.getSessionDate(),
                sessionDao.getSessionById(updatedSessionId).getSessionDate());

    }

    @Test
    public void testDeleteSession() throws ParseException {
        Date date = formatDate.parse(DATE);
        Session session = sessionDao.addSession(
                new Session(date, TIME, COST, SOLD, ACTIVE, MOVIE_ID));
        int deletedSessionId = session.getSessionId();
        LOGGER.debug("testDeleteSession({})", session);
        sessionDao.deleteSession(deletedSessionId);
        assertFalse(sessionDao.getSessionById(deletedSessionId).isSessionActive());
    }

    @Test
    public void testFilterSessionByDates() throws ParseException {
        Date fromDate = formatDate.parse(FROM_DATE);
        Date toDate = formatDate.parse(TO_DATE);
        Collection<Session> sessions = sessionDao.filterSessionByDate(fromDate, toDate);
        LOGGER.debug("testFilterSessionByDate({})", sessions);
        assertNotNull(sessions);
        assertTrue(sessions.size() > 1);
    }

    @Test
    public void testFilterSessionByNullDates() {
        Collection<Session> sessionsByDateNull
                = sessionDao.filterSessionByDate(null, null);
        Collection<Session> allSessions = sessionDao.getSessions();
        LOGGER.debug("testFilterSessionByNullDate({}, {})",
                sessionsByDateNull, allSessions);
        assertNotNull(sessionsByDateNull);
        assertNotNull(allSessions);
        assertTrue(sessionsByDateNull.size() == allSessions.size());
    }

    @Test
    public void testFilterSessionByOneNullDate() throws ParseException {
        Date fromDate = formatDate.parse(FROM_DATE);
        Date toDate = formatDate.parse(TO_DATE);
        Collection<Session> sessionsByFromDateNull
                = sessionDao.filterSessionByDate(null, toDate);
        Collection<Session> sessionsByToDateNull
                = sessionDao.filterSessionByDate(fromDate, null);
        LOGGER.debug("testFilterSessionsByFromDateNull({})", sessionsByFromDateNull);
        LOGGER.debug("testFilterSessionsByToDateNull({})", sessionsByToDateNull);
        assertNotNull(sessionsByFromDateNull);
        assertNotNull(sessionsByToDateNull);
    }
}
