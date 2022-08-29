package com.movieJDBC.demo.movieactor;

import com.movieJDBC.demo.actor.Actor;

import java.util.List;

public interface MovieActorDao {

    List<Integer> getActorsIdByMovieId(Integer id);

    void add(Integer movieId, Integer actorId);
}
