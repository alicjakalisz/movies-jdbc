package com.movieJDBC.demo.actor;

import com.movieJDBC.demo.movie.Movie;
import com.movieJDBC.demo.movie.MovieDto;

import java.util.List;

public class ActorDto {

    Integer id;
    String name;
    List<MovieDto> moviesAssigned;
}
