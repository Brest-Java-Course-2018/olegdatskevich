package com.epam.brest.course.model.dao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * POJO Movie.
 */
public class Movie {

    /**
     * Movie ID.
     */
    private int movieId;

    /**
     * Movie title.
     */
    @NotEmpty(message = "Movie title can not be empty.")
    @Size (min = 2, max = 50, message
            = "Movie title must be between 2 and 50 characters.")
    private String movieName;

    /**
     * Movie description.
     */
    @NotEmpty(message = "Movie description can not be empty.")
    @Size (min = 2, max = 50, message
            = "Movie description must be between 2 and 50 characters.")
    private String movieDescription;

    /**
     * Delete or no movie.
     */
    private boolean movieActive;

    /**
     * Default constructor.
     */
    public Movie() {
    }

    /**
     * Constructor with 3 param without ID.
     * @param movieName - title.
     * @param movieDescription - description.
     * @param movieActive - active?
     */
    public Movie(final String movieName,
                 final String movieDescription,
                 final boolean movieActive) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieActive = movieActive;
    }

    /**
     *
     * @return
     */
    public final Integer getMovieId() {
        return movieId;
    }

    /**
     *
     * @param movieId
     */
    public final void setMovieId(final Integer movieId) {
        this.movieId = movieId;
    }

    /**
     *
     * @return
     */
    public final String getMovieName() {
        return movieName;
    }

    /**
     *
     * @param movieName
     */
    public final void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     *
     * @return
     */
    public final String getMovieDescription() {
        return movieDescription;
    }

    /**
     *
     * @param movieDescription
     */
    public final void setMovieDescription(final String movieDescription) {
        this.movieDescription = movieDescription;
    }

    /**
     *
     * @return
     */
    public final boolean isMovieActive() {
        return movieActive;
    }

    /**
     *
     * @param movieActive
     */
    public final void setMovieActive(final boolean movieActive) {
        this.movieActive = movieActive;
    }

    @Override
    public final String toString() {
        return "\n\tMovie{"
                + "Id=" + movieId
                + ", Name='" + movieName + '\''
                + ", Description='" + movieDescription + '\''
                + ", Active=" + movieActive
                + '}';
    }
}
