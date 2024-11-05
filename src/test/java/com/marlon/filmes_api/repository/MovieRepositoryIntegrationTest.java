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
        assertEquals(14, result.size());

        MovieDto dto = result.get(0);
        assertEquals("Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt", dto.getProducer());
        assertEquals(2011, dto.getPreviousWin());
        assertEquals(2012, dto.getFollowingWin());
        assertEquals(1, dto.getInterval());
    }
}