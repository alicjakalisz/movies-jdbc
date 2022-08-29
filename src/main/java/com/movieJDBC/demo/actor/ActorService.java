package com.movieJDBC.demo.actor;

import com.movieJDBC.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorDaoImpl actorDaoImpl;

    @Autowired
    public ActorService(ActorDaoImpl actorDaoImpl) {
        this.actorDaoImpl = actorDaoImpl;
    }


    public List<Actor> listActors(){
       return actorDaoImpl.selectActors();
    }

    public Actor getActorById(Integer id){
        return actorDaoImpl.selectActorById(id).orElseThrow(() -> new NotFoundException(String.format("Actor with id %s not found", id)));
    }

    public Actor addActor(Actor actor){
        return actorDaoImpl.insertActor(actor);
    }

    public Actor deleteActor(Integer id){
        return actorDaoImpl.deleteActor(id).orElseThrow(() -> new NotFoundException(String
                .format("Actor with id s% does not exist",id)));
    }
}
