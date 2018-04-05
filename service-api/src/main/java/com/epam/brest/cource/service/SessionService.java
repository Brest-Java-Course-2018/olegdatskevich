package com.epam.brest.cource.service;

import com.epam.brest.course.model.dao.Session;

import java.util.Collection;
import java.util.Date;

/**
 * Service API for Session.
 */
public interface SessionService {

    /**
     * Getting all session from DB.
     * @return - collection of session.
     */
    Collection<Session> getSessions();

    /**
     * Getting one session from BD.
     * @param sessionId - id of session
     * @return session.
     */
    Session getSessionById(final int sessionId);

    /**
     * Add session in DB.
     * @param session - session that need to add.
     * @return session which was added.
     */
    Session addSession(final Session session);

    /**
     * Update session.
     * @param session which was updated
     */
    void updateSession(final Session session);

    /**
     * Deactivate session.
     * @param sessionId - id of session which was deactivated.
     */
    void deleteSession(final int sessionId);

    /**
     * Filter sessions by dates.
     * @param fromDate - start date for filter.
     * @param toDate - end date for filter.
     * @return collection of sessions.
     */
    Collection<Session> filterSessionByDate(final Date fromDate,
                                                   final Date toDate);
}
