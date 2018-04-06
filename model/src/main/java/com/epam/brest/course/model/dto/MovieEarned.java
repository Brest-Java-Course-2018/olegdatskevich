package com.epam.brest.course.model.dto;

/**
 * DTO for Movie earning.
 */
public class MovieEarned {
    private int movieId;
    private String movieName;
    private int earned;

    public MovieEarned() {
    }

    public MovieEarned(final int movieId, final String movieName,
                       final int earned) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.earned = earned;
    }

    public final int getMovieId() {
        return movieId;
    }

    public final void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    public final String getMovieName() {
        return movieName;
    }

    public final void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public final int getEarned() {
        return earned;
    }

    public final void setEarned(final int earned) {
        this.earned = earned;
    }

    @Override
    public final String toString() {
        return "\n\tMovieEarned{"
                + "Id=" + movieId
                + ", Name='" + movieName + '\''
                + ", Earned=" + earned
                + '}';
    }
}
