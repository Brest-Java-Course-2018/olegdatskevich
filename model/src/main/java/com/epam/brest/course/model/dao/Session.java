package com.epam.brest.course.model.dao;

import java.util.Date;
/**
 * POJO Session.
 */
public class Session {

    private int sessionId;
    private Date sessionDate;
    private String sessionTime;
    private int sessionCost;
    private int sessionSold;
    private boolean sessionActive;
    private int movieId;

    public Session() {
    }

    public Session(final Date sessionDate, final String sessionTime,
                   final int sessionCost, final int sessionSold,
                   final boolean sessionActive, final int movieId) {
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
        this.sessionCost = sessionCost;
        this.sessionSold = sessionSold;
        this.sessionActive = sessionActive;
        this.movieId = movieId;
    }

    public final int getSessionId() {
        return sessionId;
    }

    public final void setSessionId(final int sessionId) {
        this.sessionId = sessionId;
    }

    public final Date getSessionDate() {
        return sessionDate;
    }

    public final void setSessionDate(final Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public final String getSessionTime() {
        return sessionTime;
    }

    public final void setSessionTime(final String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public final int getSessionCost() {
        return sessionCost;
    }

    public final void setSessionCost(final int sessionCost) {
        this.sessionCost = sessionCost;
    }

    public final int getSessionSold() {
        return sessionSold;
    }

    public final void setSessionSold(final int sessionSold) {
        this.sessionSold = sessionSold;
    }

    public final boolean isSessionActive() {
        return sessionActive;
    }

    public final void setSessionActive(final boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public final int getMovieId() {
        return movieId;
    }

    public final void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    @Override
    public final String toString() {
        return "\n\tSession{"
                + "Id=" + sessionId
                + ", Date=" + sessionDate
                + ", Time='" + sessionTime + '\''
                + ", Cost=" + sessionCost
                + ", Sold=" + sessionSold
                + ", Active=" + sessionActive
                + ", movieId=" + movieId
                + '}';
    }
}
