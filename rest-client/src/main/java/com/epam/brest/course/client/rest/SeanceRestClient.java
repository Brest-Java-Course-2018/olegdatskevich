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

/**
 * Rest client for SEANCE.
 */
public class SeanceRestClient implements SeanceService {

    /**
     * Logger for SeanceRestClient.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * URL for SEANCES.
     */
    private String url;
    /**
     * For interaction with REST module.
     */
    private RestTemplate restTemplate;

    /**
     * Constructor of SeanceRestClient
     * @param url - url request.
     * @param restTemplate - REST Template
     */
    public SeanceRestClient(final String url,
                            final  RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Collection<Seance> getSeances()
            throws ServerDataAccessException {
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Seance> seances = (List<Seance>) responseEntity.getBody();
        return seances;
    }

    @Override
    public final Seance getSeanceById(final int seanceId)
            throws ServerDataAccessException {
        ResponseEntity<Seance> responseEntity = restTemplate
                .getForEntity(url + "/" + seanceId, Seance.class);
        Seance seance = responseEntity.getBody();
        LOGGER.debug("REST-client getSeanceById({})", responseEntity);
        return seance;
    }

    @Override
    public final Seance addSeance(final Seance seance)
            throws ServerDataAccessException {
        ResponseEntity<Seance> responseEntity
                = restTemplate.postForEntity(url, seance, Seance.class);
        Seance result = (Seance) responseEntity.getBody();
        LOGGER.debug("REST-client addSeance({})", responseEntity);
        return result;
    }

    @Override
    public final void updateSeance(final Seance seance)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client updateSeance({})", seance);
        restTemplate.put(url, seance);
    }

    @Override
    public final void deleteSeance(final int seanceId)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client deleteSeance({})", seanceId);
        restTemplate.put(url + "/" + seanceId, seanceId);
        //restTemplate.put(url + "/{}", seanceId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Collection<Seance> filterSeanceByDate(final Date fromDate,
                                                 final Date toDate)
            throws ServerDataAccessException {
        LOGGER.debug("REST-client filterSeanceByDate(from:{}, to:{})",
                fromDate, toDate);
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(
                        url + "/" + fromDate + "/" + toDate, List.class);
        List<Seance> seances = (List<Seance>) responseEntity.getBody();
        return seances;
    }
}
