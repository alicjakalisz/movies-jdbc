package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.rowmapper.MovieRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao{

    //todo add count rows sql query with jdbc

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {

        //Although we are selecting only 3 columns, creating new movie we should put values for all the fields
        String sql ="SELECT id, name, release_date FROM movies";

     /*   List<Movie> movieList = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Movie(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    List.of(),
                    LocalDate.parse(resultSet.getString("release_date"))
            );
        });
        return movieList;*/

       return jdbcTemplate.query(sql,new MovieRowMapper());

    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = "INSERT INTO movie(name, release_date) VALUES (?,?)";

        return jdbcTemplate.update(sql, movie.getName(), movie.getReleaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        return 0;
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        String sql = "SELECT * from movie where id = ?";
        List<Movie> query = jdbcTemplate.query(sql, new MovieRowMapper(), id);
         return query.stream().findFirst();
    }
}
