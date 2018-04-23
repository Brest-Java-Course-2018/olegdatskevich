package com.epam.brest.course.rest;

import com.epam.brest.course.model.dao.Seance;
import com.epam.brest.course.service.SeanceService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class MockTestSeanceController {

    @Autowired
    private SeanceRestController seanceRestController;

    @Autowired
    private SeanceService mockSeanceService;

    private MockMvc mockMvc;

    private static final int Seance_ID = 1;
    private static final Seance Seance = new Seance();
    private static final String DATE = "2018-05-01 00:00:00.0";
    private static final String FROM_DATE = "2018-05-01";
    private static final String TO_DATE = "2018-05-02";

    @BeforeClass
    public static void beforeClass() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);
        Seance.setSeanceId(Seance_ID);
        Seance.setSeanceDate(new Date(date.getTime()));
        Seance.setSeanceCost(5);
        Seance.setSeanceSold(25);
        Seance.setSeanceActive(true);
        Seance.setMovieId(2);
    }

    @Before
    public void setUP() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(seanceRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(mockSeanceService);
        reset(mockSeanceService);
    }

    @Test
    public void mockTestSeances() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);

        expect(mockSeanceService.getSeances()).andReturn(Arrays.asList(Seance));
        replay(mockSeanceService);

        mockMvc.perform(
                get("/seances")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]seanceId", Matchers.is(Seance_ID)))
                .andExpect(jsonPath("$[0]seanceDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("$[0]seanceCost", Matchers.is(5)))
                .andExpect(jsonPath("$[0]seanceSold", Matchers.is(25)))
                .andExpect(jsonPath("$[0]seanceActive", Matchers.is(true)))
                .andExpect(jsonPath("$[0]movieId", Matchers.is(2)));
    }

    @Test
    public void mockTestSeanceById() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);

        expect(mockSeanceService.getSeanceById(Seance_ID)).andReturn(Seance);
        replay(mockSeanceService);

        mockMvc.perform(
                get("/seances/" + Seance_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("seanceId", Matchers.is(Seance_ID)))
                .andExpect(jsonPath("seanceDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("seanceCost", Matchers.is(5)))
                .andExpect(jsonPath("seanceSold", Matchers.is(25)))
                .andExpect(jsonPath("seanceActive", Matchers.is(true)))
                .andExpect(jsonPath("movieId", Matchers.is(2)));
    }

    @Test
    public void mockTestAddSeance() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);

        expect(mockSeanceService.addSeance(anyObject())).andReturn(Seance);
        replay(mockSeanceService);

        mockMvc.perform(
                post("/seances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seanceId\":1,\"seanceDate\":1525122000000,\"seanceCost\":5,\"seanceSold\":25,\"seanceActive\":true,\"movieId\":2}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("seanceId", Matchers.is(Seance_ID)))
                .andExpect(jsonPath("seanceDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("seanceCost", Matchers.is(5)))
                .andExpect(jsonPath("seanceSold", Matchers.is(25)))
                .andExpect(jsonPath("seanceActive", Matchers.is(true)))
                .andExpect(jsonPath("movieId", Matchers.is(2)));
    }

    @Test
    public void mockTestUpdateSeance() throws Exception {
        mockSeanceService.updateSeance(anyObject());
        expectLastCall();
        replay(mockSeanceService);

        mockMvc.perform(
                put("/seances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seanceId\":1,\"seanceDate\":1525122000000,\"seanceCost\":5,\"seanceSold\":25,\"seanceActive\":true,\"movieId\":2}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockTestDeleteMovie() throws Exception {
        mockSeanceService.deleteSeance(Seance_ID);
        expectLastCall();
        replay(mockSeanceService);

        mockMvc.perform(
                put("/seances/" + Seance_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mockTestFilterByDate() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatDate.parse(DATE);
        Date fromDate = formatDate.parse(FROM_DATE);
        Date toDate = formatDate.parse(TO_DATE);

        expect(mockSeanceService.filterSeanceByDate(fromDate, toDate))
                .andReturn(Arrays.asList(Seance));
        replay(mockSeanceService);

        mockMvc.perform(
                get("/seances/" + FROM_DATE + "/" + TO_DATE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]seanceId", Matchers.is(Seance_ID)))
                .andExpect(jsonPath("$[0]seanceDate", Matchers.is(date.getTime())))
                .andExpect(jsonPath("$[0]seanceCost", Matchers.is(5)))
                .andExpect(jsonPath("$[0]seanceSold", Matchers.is(25)))
                .andExpect(jsonPath("$[0]seanceActive", Matchers.is(true)))
                .andExpect(jsonPath("$[0]movieId", Matchers.is(2)));
    }
}
