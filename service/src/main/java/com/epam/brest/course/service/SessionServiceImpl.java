package com.epam.brest.course.service;

import com.epam.brest.course.dao.SessionDao;
import com.epam.brest.course.model.dao.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * Implementation of SessionService from service-api.
 */
@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private SessionDao sessionDao;

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public final Collection<Session> getSessions() throws DataAccessException {
        Collection<Session> sessions = sessionDao.getSessions();
        LOGGER.debug("getSessions({})", sessions.size());
        return sessions;
    }

    @Override
    public final Session getSessionById(final int sessionId)
            throws DataAccessException {
        Session session = sessionDao.getSessionById(sessionId);
        LOGGER.debug("getSessionById({})", session);
        return session;
    }

    @Override
    public final Session addSession(final Session session)
            throws DataAccessException {
        Session addedSession = sessionDao.addSession(session);
        LOGGER.debug("addSession({})", session);
        return addedSession;
    }

    @Override
    public final void updateSession(final Session session)
            throws DataAccessException {
        sessionDao.updateSession(session);
        LOGGER.debug("updateSession({})", session);
    }

    @Override
    public final void deleteSession(final int sessionId)
            throws DataAccessException {
        sessionDao.deleteSession(sessionId);
        LOGGER.debug("deleteSession({})", sessionId);
    }

    @Override
    public final Collection<Session> filterSessionByDate(final Date fromDate,
                                                         final Date toDate)
            throws DataAccessException {
        Collection<Session> sessions
                = sessionDao.filterSessionByDate(fromDate, toDate);
        LOGGER.debug("filterSessionByDate({}, {})", fromDate, toDate);
        return sessions;
    }
}
