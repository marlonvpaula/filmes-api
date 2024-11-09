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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testGetIntervaloPremioWhenListIsEmpty() {
        // Configuração do cenário de teste: lista vazia
        when(movieRepository.findProducerIntervals()).thenReturn(new ArrayList<>());

        // Execução
        MovieResult result = movieService.getIntervaloPremio();

        // Verificações
        assertTrue(result.getMin().isEmpty(), "A lista de intervalos mínimos deve estar vazia.");
        assertTrue(result.getMax().isEmpty(), "A lista de intervalos máximos deve estar vazia.");
    }

    @Test
    void testGetIntervaloPremioWhenListHasOneElement() {
        List<MovieDto> list = List.of(new MovieDto("Producer1", 2000L, 2005L, 5L));
        when(movieRepository.findProducerIntervals()).thenReturn(list);

        MovieResult result = movieService.getIntervaloPremio();

        assertEquals(1, result.getMin().size(), "A lista de intervalos mínimos deve ter 1 elemento.");
        assertEquals(list.get(0), result.getMin().get(0));
        assertTrue(result.getMax().isEmpty(), "A lista de intervalos máximos deve estar vazia.");
    }

    @Test
    void testGetIntervaloPremioWhenListHasTwoElements() {
        List<MovieDto> list = List.of(
                new MovieDto("Producer1", 2000L, 2005L, 5L),
                new MovieDto("Producer2", 2010L, 2015L, 5L)
        );
        when(movieRepository.findProducerIntervals()).thenReturn(list);

        MovieResult result = movieService.getIntervaloPremio();

        assertEquals(1, result.getMin().size(), "A lista de intervalos mínimos deve ter 1 elemento.");
        assertEquals(list.get(0), result.getMin().get(0));
        assertEquals(1, result.getMax().size(), "A lista de intervalos máximos deve ter 1 elemento.");
        assertEquals(list.get(1), result.getMax().get(0));
    }

    @Test
    void testGetIntervaloPremioWhenListHasThreeElements() {
        List<MovieDto> list = List.of(
                new MovieDto("Producer1", 2000L, 2005L, 5L),
                new MovieDto("Producer2", 2006L, 2010L, 4L),
                new MovieDto("Producer3", 2011L, 2016L, 5L)
        );
        when(movieRepository.findProducerIntervals()).thenReturn(list);

        MovieResult result = movieService.getIntervaloPremio();

        assertEquals(2, result.getMin().size(), "A lista de intervalos mínimos deve ter 2 elementos.");
        assertEquals(list.get(0), result.getMin().get(0));
        assertEquals(list.get(1), result.getMin().get(1));
        assertEquals(1, result.getMax().size(), "A lista de intervalos máximos deve ter 1 elemento.");
        assertEquals(list.get(2), result.getMax().get(0));
    }

    @Test
    void testGetIntervaloPremioWhenListHasFourOrMoreElements() {
        List<MovieDto> list = List.of(
                new MovieDto("Producer1", 2000L, 2005L, 5L),
                new MovieDto("Producer2", 2006L, 2010L, 4L),
                new MovieDto("Producer3", 2011L, 2015L, 4L),
                new MovieDto("Producer4", 2016L, 2021L, 5L)
        );
        when(movieRepository.findProducerIntervals()).thenReturn(list);

        MovieResult result = movieService.getIntervaloPremio();

        assertEquals(2, result.getMin().size(), "A lista de intervalos mínimos deve ter 2 elementos.");
        assertEquals(list.get(0), result.getMin().get(0));
        assertEquals(list.get(1), result.getMin().get(1));
        assertEquals(2, result.getMax().size(), "A lista de intervalos máximos deve ter 2 elementos.");
        assertEquals(list.get(3), result.getMax().get(0));
        assertEquals(list.get(2), result.getMax().get(1));
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
