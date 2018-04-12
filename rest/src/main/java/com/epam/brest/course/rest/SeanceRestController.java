package com.epam.brest.course.rest;

import com.epam.brest.course.model.dao.Seance;
import com.epam.brest.course.service.SeanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
public class SeanceRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    private SeanceService seanceService;

    public void setSeanceService(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping(value = "/seances")
    public final Collection<Seance> seances() {
        LOGGER.debug("REST seances()");
        return seanceService.getSeances();
    }

    @GetMapping(value = "/seances/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Seance seanceById(@PathVariable(value = "id") final int id) {
        LOGGER.debug("REST seanceById({})", id);
        return seanceService.getSeanceById(id);
    }

    @PostMapping(value = "/seances")
    @ResponseStatus(HttpStatus.CREATED)
    public final Seance addSeance(@RequestBody final Seance seance) {
        LOGGER.debug("REST addSeance({})", seance);
        return seanceService.addSeance(seance);
    }

    @PutMapping(value = "/seances")
    public final void updateSeance(@RequestBody final Seance seance) {
        LOGGER.debug("REST updateSeance({})", seance);
        seanceService.updateSeance(seance);
    }

    @PutMapping(value = "/seances/{id}")
    public final void deleteSeance(@PathVariable(value = "id") final int id) {
        LOGGER.debug("REST deleteSeance({})", id);
        seanceService.deleteSeance(id);
    }

    @GetMapping(value = "/seances/{fromDate}/{toDate}")
    public final Collection<Seance> filterByDate(
            @PathVariable(value = "fromDate") final String fromDate,
            @PathVariable(value = "toDate") final String toDate)
            throws ParseException {
        LOGGER.debug("REST filterByDate({} - {})", fromDate, toDate);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatDate.parse(fromDate);
        Date endDate = formatDate.parse(toDate);
        return seanceService.filterSeanceByDate(startDate, endDate);
    }
}
