package com.marlon.filmes_api.service;

import com.marlon.filmes_api.dtos.MovieDto;
import com.marlon.filmes_api.dtos.MovieResult;
import com.marlon.filmes_api.entity.Movie;
import com.marlon.filmes_api.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        when(movieRepository.save(movie)).thenReturn(movie);

        Movie savedMovie = movieService.save(movie);

        verify(movieRepository, times(1)).save(movie);
        assertEquals("Test Movie", savedMovie.getTitle());
    }

    @Test
    void testList() {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.list();

        verify(movieRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testGetIntervaloPremio() {
        MovieDto dto1 = new MovieDto("Producer A", 2000L, 2005L, 5L);
        MovieDto dto2 = new MovieDto("Producer B", 1995L, 2003L, 8L);
        List<MovieDto> movieDtos = Arrays.asList(dto1, dto2, dto2, dto1);

        when(movieRepository.findProducerIntervals()).thenReturn(movieDtos);

        MovieResult result = movieService.getIntervaloPremio();

        verify(movieRepository, times(1)).findProducerIntervals();
        assertEquals(2, result.getMin().size());
        assertEquals(2, result.getMax().size());
        assertEquals("Producer A", result.getMin().get(0).getProducer());
        assertEquals("Producer B", result.getMax().get(1).getProducer());
    }

    @Test
    void testSaveInicial() throws Exception {
        // Configura o comportamento do repositório
        doNothing().when(movieRepository).deleteAll();
        when(movieRepository.save(any(Movie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Chama o método a ser testado
        movieService.saveInicial();

        // Verifica se o método deleteAll foi chamado
        verify(movieRepository, times(1)).deleteAll();
        // Verifica se o método save foi chamado
        verify(movieRepository, atLeastOnce()).save(any(Movie.class));
    }
}
