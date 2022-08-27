package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    Integer id;
    String name;
    List<Actor> actors;
    LocalDate releaseDate;

}
