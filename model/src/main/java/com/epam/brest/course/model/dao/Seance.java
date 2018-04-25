package com.epam.brest.course.model.dao;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * POJO Seance.
 */
public class Seance {

    /**
     * Seance ID.
     */
    private int seanceId;

    /**
     * Date of the seance.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date seanceDate;

    /**
     * Cost of the seance.
     */
    @PositiveOrZero(message = "Cost can not be negative.")
    private int seanceCost;

    /**
     * Count of sold tickets.
     */
    @PositiveOrZero(message = "Cost can not be negative.")
    private int seanceSold;

    /**
     * Active or canceled seance.
     */
    private boolean seanceActive;

    /**
     * ID of movie for showing on the seance.
     */
    private int movieId;

    /**
     * Default constructor.
     */
    public Seance() {
    }

    /**
     * Constructor with parameters.
     * @param seanceDate - date.
     * @param seanceCost - cost.
     * @param seanceSold - count of sold tickets
     * @param seanceActive - activity.
     * @param movieId - movie ID.
     */
    public Seance(final Date seanceDate, final int seanceCost,
                  final int seanceSold, final boolean seanceActive,
                  final int movieId) {
        this.seanceDate = seanceDate;
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
                + ", Cost=" + seanceCost
                + ", Sold=" + seanceSold
                + ", Active=" + seanceActive
                + ", movieId=" + movieId
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seance seance = (Seance) o;

        if (seanceId != seance.seanceId) return false;
        if (seanceCost != seance.seanceCost) return false;
        if (seanceSold != seance.seanceSold) return false;
        if (seanceActive != seance.seanceActive) return false;
        if (movieId != seance.movieId) return false;
        return seanceDate != null ? seanceDate.equals(seance.seanceDate) : seance.seanceDate == null;
    }

    @Override
    public int hashCode() {
        int result = seanceId;
        result = 31 * result + (seanceDate != null ? seanceDate.hashCode() : 0);
        result = 31 * result + seanceCost;
        result = 31 * result + seanceSold;
        result = 31 * result + (seanceActive ? 1 : 0);
        result = 31 * result + movieId;
        return result;
    }
}
