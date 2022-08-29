package com.movieJDBC.demo.util;

import com.github.javafaker.Faker;
import com.movieJDBC.demo.actor.Actor;
import com.movieJDBC.demo.actor.ActorDao;
import com.movieJDBC.demo.actor.ActorService;
import com.movieJDBC.demo.movie.Movie;
import com.movieJDBC.demo.movie.MovieDto;
import com.movieJDBC.demo.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataFakerServiceImpl implements DataFakeService{


    private MovieService movieService;
    private ActorDao actorDao;

    private ActorService actorService;



    private Faker faker;

    @Autowired
    public DataFakerServiceImpl(MovieService movieService, ActorDao actorDao, ActorService actorService) {
        this.movieService = movieService;
        this.actorDao = actorDao;
        this.actorService = actorService;
        this.faker = new Faker();
    }

    @Override
    public void fake(int actorCount, int movieCount) {

        Actor actor;
        List<Actor> actors  = new ArrayList<>();
        for (int i = 0; i < actorCount; i++) {
            actor = new Actor();
            actor.setName(faker.artist().name());
            actor = actorDao.insertActor(actor);
            actors.add(actor);

        }

        Movie movie;
        List<MovieDto> movies = new ArrayList<>();
        for (int i = 0; i <movieCount ; i++) {
            movie = new Movie();
            movie.setName(faker.superhero().name());

            MovieDto movieDto1 = movieService.addNewMovie(movie);
            movies.add(movieDto1);
        }

        //Actors(Brad Pitt, Angelina, Jennifer, David)
        for (MovieDto dto: movies) {
            int movieActors = ThreadLocalRandom.current().nextInt(1,actorCount);
            int actorId = 1; //TODO implement a method that from the full list of actors picks a random number of them
            movieService.addActorToMovie(dto.getId(), actorId);
        }

    }


}
