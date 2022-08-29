package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.movie.Movie;
import com.movieJDBC.demo.movie.MovieDto;

public class MovieToDtoConverter {

   protected static MovieDto getDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setReleaseDate(movie.getReleaseDate());
        return movieDto;
    }

}
