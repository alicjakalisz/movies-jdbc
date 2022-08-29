package com.movieJDBC.demo.actor;

import com.movieJDBC.demo.movie.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorDao {

    List<Actor> selectActors();
    Actor insertActor(Actor actor);
    Optional<Actor> deleteActor (int id);
    Optional<Actor> selectActorById(int id);

    List<Actor> getActorsByIds(List<Integer> ActorsIds);

}
