package com.movieJDBC.demo.movie;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    //TODO returns ResponseEntity always

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }



    @GetMapping
    public ResponseEntity<List<MovieDto>> listMovies() {

        List<MovieDto> movies = movieService.getMovies();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Integer id) {
        Optional<MovieDto> opMovieDto = movieService.getMovie(id);
        if(opMovieDto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {

            return ResponseEntity.ok(opMovieDto.get());
        }
    }

    @PostMapping()
    public ResponseEntity<MovieDto> addMovie(@RequestBody Movie movie) {
        try{
            MovieDto movieDto = movieService.addNewMovie(movie);
            return ResponseEntity.ok(movieDto);
        }
        catch (Exception e) {
            log.warn("Error happened while adding new movie",e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{movieId}/{actorId}")
    public ResponseEntity<MovieDto> addActorToMovie(@PathVariable("actorId") Integer actorId,@PathVariable("movieId") Integer movieId){

        //try and catch in case there is a data violation you throw exception --> to save on doing multiple connections to db

        try{
            MovieDto movieDto = movieService.addActorToMovie(movieId, actorId);
            return ResponseEntity.ok(movieDto);
        } catch(Exception e){
            log.warn("Error happens while add actor to movie",e);
            return ResponseEntity.badRequest().build();
        }
    }

    // TODO: Update movie
    //TODO : getMoviesByActor

    /**
     * /search?actorId=${actorID}&actorName=${actorName}&movieName=${movieName}
     * @param actorId
     * @param actorName
     * @param movieName
     * @return
     */
    @GetMapping("search")
    public ResponseEntity<List<MovieDto>> search(@RequestParam(required=false) Optional<Integer> actorId,
                                                 @RequestParam(required=false) Optional<String> actorName,
                                                 @RequestParam(required=false) Optional<String> movieName){

        List<MovieDto> movies = movieService.search(actorId, actorName, movieName);
        return ResponseEntity.ok(movies);
    }
}