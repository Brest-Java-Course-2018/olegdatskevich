package com.epam.brest.course.service;

import com.epam.brest.cource.service.SessionService;
import com.epam.brest.course.model.dao.Session;

import java.util.Collection;
import java.util.Date;

public class SessionServiceImpl implements SessionService {

    @Override
    public Collection<Session> serviceGetSessions() {
        return null;
    }

    @Override
    public Session serviceGetSessionById(final int sessionId) {
        return null;
    }

    @Override
    public Session serviceAddSession(final Session session) {
        return null;
    }

    @Override
    public void serviceUpdateSession(final Session session) {

    }

    @Override
    public void serviceDeleteSession(final int sessionId) {

    }

    @Override
    public Collection<Session> serviceFilterSessionByDate(final Date fromDate,
                                                          final Date toDate) {
        return null;
    }
}
