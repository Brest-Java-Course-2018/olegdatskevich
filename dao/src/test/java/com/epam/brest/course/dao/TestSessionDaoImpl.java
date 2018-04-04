package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String FROM_DATE = "2018-04-03";
    private static final String TO_DATE = "2018-04-04";

    @Autowired
    private SessionDao sessionDao;

    @Test
    public void testGetSessions() {
        Collection<Session> sessions = sessionDao.getSessions();
        LOGGER.debug("testGetSessions({})", sessions);
        assertFalse(sessions.isEmpty());
    }

    @Test
    public void testGetSessionById() {

    }

    @Test
    public void testAddSession() throws ParseException {
        Date date = formatDate.parse(DATE);
        Collection<Session> sessions = sessionDao.getSessions();
        int sizeBefore = sessions.size();
        System.out.println(sizeBefore);
        Session session = new Session(date, TIME, COST, SOLD, ACTIVE, MOVIE_ID);
        Session addedSession = sessionDao.addSession(session);
        assertNotNull(addedSession);
        assertTrue(session.getSessionDate().equals(addedSession.getSessionDate()));
        System.out.println(sessionDao.getSessions().size());
        assertTrue((sizeBefore + 1) == sessionDao.getSessions().size());
    }
}
