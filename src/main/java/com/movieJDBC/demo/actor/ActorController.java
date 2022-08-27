package com.movieJDBC.demo.actor;

import com.movieJDBC.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/actor")
public class ActorController {

    private  final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Actor>> listActors(){
        List<Actor> actors = actorService.listActors();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Integer id){
        try{
            Actor actorById = actorService.getActorById(id);
            return ResponseEntity.ok(actorById);
        }
        catch (NotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.addActor(actor));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteActor(@PathVariable("id") Integer id){
        try{
            Actor actor = actorService.deleteActor(id);
            return ResponseEntity.ok(actor);
        }catch(NotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(String.format("Actor with id %s does not exist",id));
        }

    }
}
