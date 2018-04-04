package com.epam.brest.course.model.dao;

public class Movie {
    private int movieId;
    private String movieName;
    private String movieDescription;
    private boolean movieActive;

    public Movie() {
    }

    public Movie(String movieName,
                 String movieDescription,
                 boolean movieActive) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieActive = movieActive;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public boolean isMovieActive() {
        return movieActive;
    }

    public void setMovieActive(boolean movieActive) {
        this.movieActive = movieActive;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieIsActive=" + movieActive +
                '}';
    }
}
