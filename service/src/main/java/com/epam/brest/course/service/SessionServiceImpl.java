package com.epam.brest.course.service;

import com.epam.brest.cource.service.SessionService;
import com.epam.brest.course.model.dao.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * Implementation of SessionService from service-api.
 */
@Transactional
public class SessionServiceImpl implements SessionService {

    @Override
    public final Collection<Session> getSessions() {
        return null;
    }

    @Override
    public final Session getSessionById(final int sessionId) {
        return null;
    }

    @Override
    public final Session addSession(final Session session) {
        return null;
    }

    @Override
    public final void updateSession(final Session session) {

    }

    @Override
    public final void deleteSession(final int sessionId) {

    }

    @Override
    public final Collection<Session> filterSessionByDate(final Date fromDate,
                                                         final Date toDate) {
        return null;
    }
}
