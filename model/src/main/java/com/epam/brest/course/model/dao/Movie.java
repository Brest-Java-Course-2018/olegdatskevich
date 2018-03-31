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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        if (movieId != movie.movieId) {
            return false;
        }
        if (movieActive != movie.movieActive) {
            return false;
        }
        if (movieName != null ? !movieName.equals(movie.movieName)
                : movie.movieName != null) {
            return false;
        }
        return movieDescription != null
                ? movieDescription.equals(movie.movieDescription)
                : movie.movieDescription == null;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
        result = 31 * result
                + (movieDescription != null ? movieDescription.hashCode() : 0);
        result = 31 * result + (movieActive ? 1 : 0);
        return result;
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
