package com.movieJDBC.demo.actor;

import com.movieJDBC.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorDataAccessService actorDataAccessService;

    @Autowired
    public ActorService(ActorDataAccessService actorDataAccessService) {
        this.actorDataAccessService = actorDataAccessService;
    }


    public List<Actor> listActors(){
       return actorDataAccessService.selectActors();
    }

    public Actor getActorById(Integer id){
        return actorDataAccessService.selectActorById(id).orElseThrow(() -> new NotFoundException(String.format("Actor with id %s not found", id)));
    }

    public Actor addActor(Actor actor){
        return actorDataAccessService.insertActor(actor);
    }

    public Actor deleteActor(Integer id){
        return actorDataAccessService.deleteActor(id).orElseThrow(() -> new NotFoundException(String
                .format("Actor with id s% does not exist",id)));
    }
}
