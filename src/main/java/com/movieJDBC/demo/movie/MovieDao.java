package com.movieJDBC.demo.movie;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MovieDao {
    List<Movie> selectMovies();
    MovieDto insertMovie(Movie movie);
    int deleteMovie(int id);
    Optional<Movie> selectMovieById(int id);

    List<MovieDto> search(Optional<Integer> actorId, Optional<String> actorName, Optional<String> movieName);
    // TODO: Update

}
