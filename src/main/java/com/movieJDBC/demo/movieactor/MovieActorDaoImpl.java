package com.movieJDBC.demo.movieactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieActorDaoImpl implements MovieActorDao{

    private  JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieActorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> getActorsIdByMovieId(Integer id) {

        String sql = "SELECT ACTOR_ID from MOVIE_ACTOR WHERE MOVIE_ID = ?";

        return jdbcTemplate.queryForList(sql, Integer.class,id);

    }

    @Override
    public void add(Integer movieId, Integer actorId) {
        String sql = "INSERT INTO MOVIE_ACTOR(MOVIE_ID, ACTOR_ID) VALUES (?,?)";
        jdbcTemplate.update(sql,movieId,actorId);
    }
}
