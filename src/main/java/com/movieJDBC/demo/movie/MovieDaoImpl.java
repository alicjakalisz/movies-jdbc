package com.movieJDBC.demo.movie;

import com.movieJDBC.demo.rowmapper.MovieRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDaoImpl implements MovieDao{

    //todo add count rows sql query with jdbc

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public MovieDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {

        //Although we are selecting only 3 columns, creating new movie we should put values for all the fields
        String sql ="SELECT id, name, release_date FROM MOVIE";

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
    public MovieDto insertMovie(Movie movie) {
        String sql = "INSERT into MOVIE(name, release_date) VALUES (?,?)";

        int update = jdbcTemplate.update(sql, movie.getName(), movie.getReleaseDate());
        String sql2 = "SELECT * from MOVIE ORDER BY ID DESC LIMIT 1";
        Movie m = jdbcTemplate.query(sql2, new MovieRowMapper()).stream().findFirst().get();
        return   MovieToDtoConverter.getDto(m);
    }

    @Override
    public int deleteMovie(int id) {
        return 0;
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        String sql = "SELECT * from MOVIE where id = ?";
        List<Movie> query = jdbcTemplate.query(sql, new MovieRowMapper(), id);
         return query.stream().findFirst();
    }

    @Override
    public List<MovieDto> search(Optional<Integer> actorId, Optional<String> actorName, Optional<String> movieName) {

        return null;
    }


}
