package com.epam.brest.course.model.dao;

public class Movie {
    private int movieId;
    private String movieMame;
    private String movieDescription;
    private boolean movieIsActive;

    public Movie() {
    }

    public Movie(String movieMame, String movieDescription, boolean movieIsActive) {
        this.movieMame = movieMame;
        this.movieDescription = movieDescription;
        this.movieIsActive = movieIsActive;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieMame() {
        return movieMame;
    }

    public void setMovieMame(String movieMame) {
        this.movieMame = movieMame;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public boolean isMovieIsActive() {
        return movieIsActive;
    }

    public void setMovieIsActive(boolean movieIsActive) {
        this.movieIsActive = movieIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (movieId != movie.movieId) return false;
        if (movieIsActive != movie.movieIsActive) return false;
        if (movieMame != null ? !movieMame.equals(movie.movieMame) : movie.movieMame != null)
            return false;
        return movieDescription != null ? movieDescription.equals(movie.movieDescription) : movie.movieDescription == null;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (movieMame != null ? movieMame.hashCode() : 0);
        result = 31 * result + (movieDescription != null ? movieDescription.hashCode() : 0);
        result = 31 * result + (movieIsActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieMame='" + movieMame + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieIsActive=" + movieIsActive +
                '}';
    }
}
