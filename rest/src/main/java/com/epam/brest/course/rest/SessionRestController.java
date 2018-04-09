package com.epam.brest.course.rest;

import com.epam.brest.course.model.dao.Session;
import com.epam.brest.course.service.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
public class SessionRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    private SessionService sessionService;

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/sessions")
    public final Collection<Session> sessions() {
        LOGGER.debug("REST sessions()");
        return sessionService.getSessions();
    }

    @GetMapping(value = "/sessions/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Session sessionById(@PathVariable(value = "id") final int id) {
        LOGGER.debug("REST sessionById({})", id);
        return sessionService.getSessionById(id);
    }

    @PostMapping(value = "/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public final Session addSession(@RequestBody final Session session) {
        LOGGER.debug("REST addSession({})", session);
        return sessionService.addSession(session);
    }

    @PutMapping(value = "/sessions")
    public final void updateSession(@RequestBody final Session session) {
        LOGGER.debug("REST updateSession({})", session);
        sessionService.updateSession(session);
    }

    @PutMapping(value = "/sessions/{id}")
    public final void deleteSession(@PathVariable(value = "id") final int id) {
        LOGGER.debug("REST deleteSession({})", id);
        sessionService.deleteSession(id);
    }

    @GetMapping(value = "/sessions/{fromDate}/{toDate}")
    public final Collection<Session> filterByDate(
            @PathVariable(value = "fromDate") final String fromDate,
            @PathVariable(value = "toDate") final String toDate)
            throws ParseException {
        LOGGER.debug("REST filterByDate({} - {})", fromDate, toDate);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatDate.parse(fromDate);
        Date endDate = formatDate.parse(toDate);
        return sessionService.filterSessionByDate(startDate, endDate);
    }
}
