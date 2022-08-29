package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.actor.Actor;
import com.movieJDBC.demo.actor.ActorDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class MovieDto {

    Integer id;
    String name;
    List<Actor> actors;
    LocalDate releaseDate;

}
