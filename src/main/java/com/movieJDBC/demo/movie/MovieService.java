package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.actor.Actor;
import com.movieJDBC.demo.actor.ActorDao;
import com.movieJDBC.demo.exception.NotFoundException;
import com.movieJDBC.demo.movieactor.MovieActorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieService {

    private final MovieDao movieDao;

    private final ActorDao actorDao;

    private MovieActorDao movieActorDao;



    @Autowired
    public MovieService(MovieDao movieDao, ActorDao actorDao, MovieActorDao movieActorDao) {
        this.movieDao = movieDao;
        this.actorDao = actorDao;
        this.movieActorDao = movieActorDao;
    }

    public List<MovieDto> getMovies() {
        List<Movie> movies = movieDao.selectMovies();
        return movies.stream().map(movie -> MovieToDtoConverter.getDto(movie)).collect(Collectors.toList());

    }

    public MovieDto addNewMovie(Movie movie) {
        MovieDto movieDto = movieDao.insertMovie(movie);
        return movieDto;
    }

    public void deleteMovie(Integer id) {
        Optional<Movie> movies = movieDao.selectMovieById(id);
        movies.ifPresentOrElse(movie -> {
            int result = movieDao.deleteMovie(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete movie");
            }
        }, () -> {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        });
    }

    public Optional<MovieDto> getMovie(int id) {
        Optional<Movie> movie = movieDao.selectMovieById(id);

        if(movie.isPresent()){
            List<Integer> actorsIdByMovieId = movieActorDao.getActorsIdByMovieId(movie.get().getId());
            List<Actor> actors = actorDao.getActorsByIds(actorsIdByMovieId);
            MovieDto dto = MovieToDtoConverter.getDto(movie.get());
            dto.setActors(actors);
            return Optional.of(dto);
        }
        else{
            return Optional.empty();
        }
    }
    public MovieDto addActorToMovie(Integer movieId, Integer actorId){
        MovieDto movieDto = new MovieDto();
        return movieDto;
    }

    public List<MovieDto> search(Optional<Integer> actorId, Optional<String> actorName, Optional<String> movieName) {
        return movieDao.search(actorId,actorName,movieName);
    }
}

