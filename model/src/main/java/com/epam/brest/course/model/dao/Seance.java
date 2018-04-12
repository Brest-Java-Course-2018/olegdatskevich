package com.epam.brest.course.model.dao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * POJO Seance.
 */
public class Seance {

    private int seanceId;

    @NotNull(message = "Seance date can not be null.")
    private Date seanceDate;

    @NotEmpty(message = "Seance time can not be empty.")
    @NotNull(message = "Seance time can not be null.")
    private String seanceTime;

    @NotNull(message = "Cost can not be null.")
    @PositiveOrZero(message = "Cost can not be negative.")
    private int seanceCost;
    private int seanceSold;
    private boolean seanceActive;
    private int movieId;

    public Seance() {
    }

    public Seance(final Date seanceDate, final String seanceTime,
                   final int seanceCost, final int seanceSold,
                   final boolean seanceActive, final int movieId) {
        this.seanceDate = seanceDate;
        this.seanceTime = seanceTime;
        this.seanceCost = seanceCost;
        this.seanceSold = seanceSold;
        this.seanceActive = seanceActive;
        this.movieId = movieId;
    }

    public final int getSeanceId() {
        return seanceId;
    }

    public final void setSeanceId(final int seanceId) {
        this.seanceId = seanceId;
    }

    public final Date getSeanceDate() {
        return seanceDate;
    }

    public final void setSeanceDate(final Date seanceDate) {
        this.seanceDate = seanceDate;
    }

    public final String getSeanceTime() {
        return seanceTime;
    }

    public final void setSeanceTime(final String seanceTime) {
        this.seanceTime = seanceTime;
    }

    public final int getSeanceCost() {
        return seanceCost;
    }

    public final void setSeanceCost(final int seanceCost) {
        this.seanceCost = seanceCost;
    }

    public final int getSeanceSold() {
        return seanceSold;
    }

    public final void setSeanceSold(final int seanceSold) {
        this.seanceSold = seanceSold;
    }

    public final boolean isSeanceActive() {
        return seanceActive;
    }

    public final void setSeanceActive(final boolean seanceActive) {
        this.seanceActive = seanceActive;
    }

    public final int getMovieId() {
        return movieId;
    }

    public final void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    @Override
    public final String toString() {
        return "\n\tSeance{"
                + "Id=" + seanceId
                + ", Date=" + seanceDate
                + ", Time='" + seanceTime + '\''
                + ", Cost=" + seanceCost
                + ", Sold=" + seanceSold
                + ", Active=" + seanceActive
                + ", movieId=" + movieId
                + '}';
    }
}
