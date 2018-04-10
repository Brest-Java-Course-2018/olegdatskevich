package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import com.epam.brest.course.model.dao.Session;
import com.epam.brest.course.service.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SessionRestClient implements SessionService {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url;
    private RestTemplate restTemplate;

    public SessionRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Session> getSessions() throws ServerDataAccessException {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Session> sessions = (List<Session>)responseEntity.getBody();
        return sessions;
    }

    @Override
    public Session getSessionById(int sessionId)
            throws ServerDataAccessException {
        ResponseEntity<Session> responseEntity = restTemplate
                .getForEntity(url + "/" + sessionId, Session.class);
        Session session = responseEntity.getBody();
        LOGGER.debug("REST-client getSessionById({})", responseEntity);
        return session;
    }

    @Override
    public Session addSession(Session session)
            throws ServerDataAccessException {
        ResponseEntity<Session> responseEntity
                = restTemplate.postForEntity(url, session, Session.class);
        Session result = (Session)responseEntity.getBody();
        LOGGER.debug("REST-client addSession({})", responseEntity);
        return result;
    }

    @Override
    public void updateSession(Session session)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client updateSession({})", session);
        restTemplate.put(url, session);
    }

    @Override
    public void deleteSession(int sessionId) throws ServerDataAccessException {
        LOGGER.debug("REST-client deleteSession({})", sessionId);
        restTemplate.put(url + "/" + sessionId, sessionId);
        //restTemplate.put(url + "/{}", sessionId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Session> filterSessionByDate(Date fromDate, Date toDate)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client filterSessionByDate(from:{}, to:{})",
                fromDate, toDate);
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(
                        url + "/" + fromDate + "/" + toDate, List.class);
        List<Session> sessions = (List<Session>)responseEntity.getBody();
        return sessions;
    }
}
