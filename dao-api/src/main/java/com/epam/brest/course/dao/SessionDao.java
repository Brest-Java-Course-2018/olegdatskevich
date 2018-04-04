package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Session;

import java.util.Collection;
import java.util.Date;

public interface SessionDao {

    Collection<Session> getSessions();

    Session getSessionById(final int sessionId);

    Session addSession(final Session session);

    void updateSession(final Session session);

    void deleteSession(final int sessionId);

    Collection<Session> filterSessionByDate(final Date fromDate,
                                            final Date toDate);
}
