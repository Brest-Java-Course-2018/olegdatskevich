package com.epam.brest.cource.service;

import com.epam.brest.course.model.dao.Session;

import java.util.Collection;
import java.util.Date;

public interface SessionService {

    Collection<Session> serviceGetSessions();

    Session serviceGetSessionById(final int sessionId);

    Session serviceAddSession(final Session session);

    void serviceUpdateSession(final Session session);

    void serviceDeleteSession(final int sessionId);

    Collection<Session> serviceFilterSessionByDate(final Date fromDate,
                                                   final Date toDate);
}
