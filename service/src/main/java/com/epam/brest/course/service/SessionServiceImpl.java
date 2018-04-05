package com.epam.brest.course.service;

import com.epam.brest.cource.service.SessionService;
import com.epam.brest.course.model.dao.Session;

import java.util.Collection;
import java.util.Date;

public class SessionServiceImpl implements SessionService {

    @Override
    public Collection<Session> getSessions() {
        return null;
    }

    @Override
    public Session getSessionById(int sessionId) {
        return null;
    }

    @Override
    public Session addSession(Session session) {
        return null;
    }

    @Override
    public void updateSession(Session session) {

    }

    @Override
    public void deleteSession(int sessionId) {

    }

    @Override
    public Collection<Session> filterSessionByDate(Date fromDate, Date toDate) {
        return null;
    }
}
