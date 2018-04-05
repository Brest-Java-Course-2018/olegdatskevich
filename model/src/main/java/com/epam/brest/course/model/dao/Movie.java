package com.epam.brest.course.model.dao;
/**
 * POJO Movie.
 */
public class Movie {

    private int movieId;
    private String movieName;
    private String movieDescription;
    private boolean movieActive;

    public Movie() {
    }

    public Movie(final String movieName,
                 final String movieDescription,
                 final boolean movieActive) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieActive = movieActive;
    }

    public final Integer getMovieId() {
        return movieId;
    }

    public final void setMovieId(final Integer movieId) {
        this.movieId = movieId;
    }

    public final String getMovieName() {
        return movieName;
    }

    public final void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public final String getMovieDescription() {
        return movieDescription;
    }

    public final void setMovieDescription(final String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public final boolean isMovieActive() {
        return movieActive;
    }

    public final void setMovieActive(final boolean movieActive) {
        this.movieActive = movieActive;
    }
}
