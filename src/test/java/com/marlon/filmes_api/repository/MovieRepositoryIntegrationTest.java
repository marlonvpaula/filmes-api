package com.marlon.filmes_api.repository;

import com.marlon.filmes_api.dtos.MovieDto;
import com.marlon.filmes_api.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MovieRepositoryIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;


    @Test
    void testFindProducerIntervals() {
        List<MovieDto> result = movieRepository.findProducerIntervals();

        assertNotNull(result);
        assertEquals(1, result.size());

        MovieDto dto = result.get(0);
        assertEquals("Bo Derek", dto.getProducer());
        assertEquals(1984, dto.getPreviousWin());
        assertEquals(1990, dto.getFollowingWin());
        assertEquals(6, dto.getInterval());
    }
}