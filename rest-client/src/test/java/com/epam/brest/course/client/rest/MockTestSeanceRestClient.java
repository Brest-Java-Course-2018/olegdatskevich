package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.dao.Seance;
import com.epam.brest.course.service.SeanceService;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class MockTestSeanceRestClient {

    @Autowired
    private SeanceService mockSeanceService;

    @Autowired
    private RestTemplate mockRestTemplate;

    private static final int Seance_ID = 1;
    private static final Seance Seance_1 = new Seance();
    private static final Seance Seance_2 = new Seance();

    @BeforeClass
    public static void beforeClass() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Seance_1.setSeanceId(1);
        Seance_1.setSeanceDate(formatDate.parse("2018-05-01"));
        Seance_1.setSeanceTime("13:00:00");
        Seance_1.setSeanceCost(5);
        Seance_1.setSeanceSold(25);
        Seance_1.setSeanceActive(true);
        Seance_1.setMovieId(1);
        Seance_2.setSeanceId(1);
        Seance_2.setSeanceDate(formatDate.parse("2018-05-02"));
        Seance_2.setSeanceTime("13:00:00");
        Seance_2.setSeanceCost(6);
        Seance_2.setSeanceSold(26);
        Seance_2.setSeanceActive(true);
        Seance_2.setMovieId(2);
    }

    @After
    public void tearDown() {
        verify(mockRestTemplate);
        reset(mockRestTemplate);
    }

    @Test
    public void mockTestSeancesClient() {
        List<Seance> seances = Arrays.asList(Seance_1, Seance_2);
        ResponseEntity entity = new ResponseEntity<>(seances, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);

        Collection<Seance> results = mockSeanceService.getSeances();
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void mockTestFilterByDateClient() {
        List<Seance> seancesByDate = Arrays.asList(Seance_1, Seance_2);
        ResponseEntity entity = new ResponseEntity<>(seancesByDate, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject())).andReturn(entity);
        replay(mockRestTemplate);

        Collection<Seance> results = mockSeanceService.filterSeanceByDate(
                Seance_1.getSeanceDate(), Seance_2.getSeanceDate());
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void mockTestSeanceByIdClient() {
        ResponseEntity entity = new ResponseEntity<>(Seance_1, HttpStatus.FOUND);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);

        Seance result = mockSeanceService.getSeanceById(Seance_1.getSeanceId());
        assertNotNull(result);
        assertEquals(Seance_1, result);
    }

    @Test
    public void mockTestAddSeanceClient() {
        ResponseEntity entity = new ResponseEntity<>(Seance_1, HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(anyString(), anyObject(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);

        Seance result = mockSeanceService.addSeance(Seance_1);
        assertNotNull(result);
        assertEquals(Seance_1, result);
    }

    @Test
    public void mockTestUpdateSeanceClient() {
        mockRestTemplate.put(anyString(), anyObject());
        expectLastCall();
        replay(mockRestTemplate);
        mockSeanceService.updateSeance(Seance_1);
    }

    @Test
    public void mockTestDeleteSeanceClient() {
        mockRestTemplate.put(anyString(), anyObject());
        expectLastCall();
        replay(mockRestTemplate);
        mockSeanceService.deleteSeance(Seance_ID);
    }
}
