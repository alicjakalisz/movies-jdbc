package com.movieJDBC.demo.util;

import com.github.javafaker.Faker;
import com.movieJDBC.demo.actor.Actor;
import com.movieJDBC.demo.actor.ActorDao;
import com.movieJDBC.demo.actor.ActorService;
import com.movieJDBC.demo.movie.Movie;
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
    public int fakeActorData(int count) {

        Actor actor;
        int i;
        for (i = 0; i < count; i++) {
            actor = new Actor();
            actor.setName(faker.artist().name());
            actorDao.insertActor(actor);

        }

        return i;
    }

    @Override
    public int fakeMovieData(int count) {
        Movie movie;
        int i;
        for (i = 0; i <count ; i++) {
            movie = new Movie();
            movie.setName(faker.superhero().name());

            movie.setActors(getRandomActorsFromDb());
            movieService.addNewMovie(movie);
        }

        return i;
    }

    private List<Actor> getRandomActorsFromDb(){
        int max = actorService.listActors().size();
        List<Actor> list = new ArrayList<>();
        int randomActorId;
        int randomNumberOfActors = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        for (int i = 0; i < randomNumberOfActors; i++) {
            randomActorId =ThreadLocalRandom.current().nextInt(1, max);
           list.add(actorService.getActorById(randomActorId));
        }
        return list;
    }

}
