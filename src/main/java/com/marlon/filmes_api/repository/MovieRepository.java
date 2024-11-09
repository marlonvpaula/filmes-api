package com.marlon.filmes_api.repository;

import com.marlon.filmes_api.dtos.MovieDto;
import com.marlon.filmes_api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT new com.marlon.filmes_api.dtos.MovieDto(m.producers, MIN(m.yearMovie), MAX(m.yearMovie), ABS(MAX(m.yearMovie) - MIN(m.yearMovie))) " +
            "FROM Movie m " +
            "where m.winner = 'yes'" +
            "GROUP BY m.producers " +
            "HAVING COUNT(*) > 1" +
            "ORDER BY ABS(MAX(m.yearMovie) - MIN(m.yearMovie))")
    List<MovieDto> findProducerIntervals();

}
