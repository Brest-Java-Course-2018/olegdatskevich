package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import com.epam.brest.course.model.dao.Seance;
import com.epam.brest.course.service.SeanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SeanceRestClient implements SeanceService {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url;
    private RestTemplate restTemplate;

    public SeanceRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Seance> getSeances() throws ServerDataAccessException {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Seance> seances = (List<Seance>)responseEntity.getBody();
        return seances;
    }

    @Override
    public Seance getSeanceById(int seanceId)
            throws ServerDataAccessException {
        ResponseEntity<Seance> responseEntity = restTemplate
                .getForEntity(url + "/" + seanceId, Seance.class);
        Seance seance = responseEntity.getBody();
        LOGGER.debug("REST-client getSeanceById({})", responseEntity);
        return seance;
    }

    @Override
    public Seance addSeance(Seance seance)
            throws ServerDataAccessException {
        ResponseEntity<Seance> responseEntity
                = restTemplate.postForEntity(url, seance, Seance.class);
        Seance result = (Seance)responseEntity.getBody();
        LOGGER.debug("REST-client addSeance({})", responseEntity);
        return result;
    }

    @Override
    public void updateSeance(Seance seance)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client updateSeance({})", seance);
        restTemplate.put(url, seance);
    }

    @Override
    public void deleteSeance(int seanceId) throws ServerDataAccessException {
        LOGGER.debug("REST-client deleteSeance({})", seanceId);
        restTemplate.put(url + "/" + seanceId, seanceId);
        //restTemplate.put(url + "/{}", seanceId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Seance> filterSeanceByDate(Date fromDate, Date toDate)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client filterSeanceByDate(from:{}, to:{})",
                fromDate, toDate);
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(
                        url + "/" + fromDate + "/" + toDate, List.class);
        List<Seance> seances = (List<Seance>)responseEntity.getBody();
        return seances;
    }
}
