package com.movieJDBC.demo.rowmapper;

import com.movieJDBC.demo.actor.Actor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorRowMapper implements RowMapper<Actor> {

    @Override
    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Actor actor = new Actor();
        actor.setName(rs.getString("name"));
        actor.setId(rs.getInt("id"));
        return actor;
    }
}
