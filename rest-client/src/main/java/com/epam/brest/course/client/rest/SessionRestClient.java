package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.dao.Session;
import com.epam.brest.course.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SessionRestClient implements SessionService {

    private String url;
    private RestTemplate restTemplate;

    public SessionRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Session> getSessions() {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Session> sessions = (List<Session>)responseEntity.getBody();
        return sessions;
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
