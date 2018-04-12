package com.epam.brest.course.model.dao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * POJO Seance.
 */
public class Seance {

    /**
     *
     */
    private int seanceId;

    /**
     *
     */
    @NotNull(message = "Seance date can not be null.")
    private Date seanceDate;

    /**
     *
     */
    @NotEmpty(message = "Seance time can not be empty.")
    @NotNull(message = "Seance time can not be null.")
    private String seanceTime;

    /**
     *
     */
    @NotNull(message = "Cost can not be null.")
    @PositiveOrZero(message = "Cost can not be negative.")
    private int seanceCost;
    /**
     *
     */
    private int seanceSold;
    /**
     *
     */
    private boolean seanceActive;
    /**
     *
     */
    private int movieId;

    /**
     *
     */
    public Seance() {
    }

    /**
     *
     * @param seanceDate
     * @param seanceTime
     * @param seanceCost
     * @param seanceSold
     * @param seanceActive
     * @param movieId
     */
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

    /**
     *
     * @return
     */
    public final int getSeanceId() {
        return seanceId;
    }

    /**
     *
     * @param seanceId
     */
    public final void setSeanceId(final int seanceId) {
        this.seanceId = seanceId;
    }

    /**
     *
     * @return
     */
    public final Date getSeanceDate() {
        return seanceDate;
    }

    /**
     *
     * @param seanceDate
     */
    public final void setSeanceDate(final Date seanceDate) {
        this.seanceDate = seanceDate;
    }

    /**
     *
     * @return
     */
    public final String getSeanceTime() {
        return seanceTime;
    }

    /**
     *
     * @param seanceTime
     */
    public final void setSeanceTime(final String seanceTime) {
        this.seanceTime = seanceTime;
    }

    /**
     *
     * @return
     */
    public final int getSeanceCost() {
        return seanceCost;
    }

    /**
     *
     * @param seanceCost
     */
    public final void setSeanceCost(final int seanceCost) {
        this.seanceCost = seanceCost;
    }

    /**
     *
     * @return
     */
    public final int getSeanceSold() {
        return seanceSold;
    }

    /**
     *
     * @param seanceSold
     */
    public final void setSeanceSold(final int seanceSold) {
        this.seanceSold = seanceSold;
    }

    /**
     *
     * @return
     */
    public final boolean isSeanceActive() {
        return seanceActive;
    }

    /**
     *
     * @param seanceActive
     */
    public final void setSeanceActive(final boolean seanceActive) {
        this.seanceActive = seanceActive;
    }

    /**
     *
     * @return
     */
    public final int getMovieId() {
        return movieId;
    }

    /**
     *
     * @param movieId
     */
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
