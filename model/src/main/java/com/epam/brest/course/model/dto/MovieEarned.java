package com.epam.brest.course.model.dto;

public class MovieEarned {
    private int movieId;
    private String movieName;
    private int earned;

    public MovieEarned() {
    }

    public MovieEarned(int movieId, String movieName, int earned) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.earned = earned;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieEarned that = (MovieEarned) o;

        if (movieId != that.movieId) return false;
        if (earned != that.earned) return false;
        return movieName != null ? movieName.equals(that.movieName) : that.movieName == null;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
        result = 31 * result + earned;
        return result;
    }

    @Override
    public String toString() {
        return "MovieEarned{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", earned=" + earned +
                '}';
    }
}
