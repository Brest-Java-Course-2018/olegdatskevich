package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Session;

import java.util.Collection;
import java.util.Date;

/**
 * Session DAO interface.
 */
public interface SessionDao {

    /**
     * Getting session.
     * @return collection of session.
     */
    Collection<Session> getSessions();

    /**
     * Getting one session by id.
     * @param sessionId - session id.
     * @return session.
     */
    Session getSessionById(final int sessionId);

    /**
     * Add session in DB.
     * @param session - session for adding.
     * @return added session.
     */
    Session addSession(final Session session);

    /**
     * Update session.
     * @param session - session for updating
     */
    void updateSession(final Session session);

    /**
     * Delete session by session id.
     * @param sessionId - session id for deleting.
     */
    void deleteSession(final int sessionId);

    /**
     * Filter session by dates.
     * @param fromDate - start date.
     * @param toDate - end date.
     * @return collection of session.
     */
    Collection<Session> filterSessionByDate(final Date fromDate,
                                            final Date toDate);
}
