package com.movieJDBC.demo.actor;

import com.movieJDBC.demo.rowmapper.ActorRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDataAccessService implements ActorDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ActorDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Actor> selectActors() {
        String sql = "SELECT * FROM Actor";
        List<Actor> actors = jdbcTemplate.query(sql, new ActorRowMapper());
        return actors;
    }

    @Override
    public Actor insertActor(Actor actor) {
        String sql = "INSERT INTO Actor (name) VALUES(?)";

        // 1.Persisting new Actor:
        int resultWithInt = jdbcTemplate.update(sql,actor.getName());
        //2. Getting the lastly added

        String sql2 = "SELECT * FROM Actor ORDER BY id desc limit 1";
        List<Actor> actorList = jdbcTemplate.query(sql2, new ActorRowMapper());
        return actorList.stream().findFirst().get();


    }

    @Override
    public Optional<Actor> deleteActor(int id) {
        Optional<Actor> toRemove = selectActorById(id);
        String sql = "DELETE from Actor where id = ?";
        int update = jdbcTemplate.update(sql, id);
        if(update ==0 ){
            return Optional.empty();
        } else {
            return toRemove;
        }
    }

    @Override
    public Optional<Actor> selectActorById(int id) {
        String sql = "SELECT * FROM Actor where id = ?";
        List<Actor> actorList = jdbcTemplate.query(sql, new ActorRowMapper(), id);
        return actorList.stream().findFirst();
    }
}
