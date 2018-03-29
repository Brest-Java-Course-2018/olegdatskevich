package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collection;

public class SessionDaoImpl implements SessionDao {

    private static final Logger LOGGER
            = LogManager.getLogger(MovieDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

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
}
