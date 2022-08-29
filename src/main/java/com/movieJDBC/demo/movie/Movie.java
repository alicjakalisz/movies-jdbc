package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    Integer id;
    String name;
    LocalDate releaseDate;

}
