package com.epam.brest.course.model.dao;

import java.util.Date;

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

    public Session(Date sessionDate, String sessionTime,
                   int sessionCost, int sessionSold, boolean sessionActive,
                   int movieId) {
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
        this.sessionCost = sessionCost;
        this.sessionSold = sessionSold;
        this.sessionActive = sessionActive;
        this.movieId = movieId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public int getSessionCost() {
        return sessionCost;
    }

    public void setSessionCost(int sessionCost) {
        this.sessionCost = sessionCost;
    }

    public int getSessionSold() {
        return sessionSold;
    }

    public void setSessionSold(int sessionSold) {
        this.sessionSold = sessionSold;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionDate=" + sessionDate +
                ", sessionTime='" + sessionTime + '\'' +
                ", sessionCost=" + sessionCost +
                ", sessionSold=" + sessionSold +
                ", sessionIsActive=" + sessionActive +
                ", movieId=" + movieId +
                '}';
    }
}
