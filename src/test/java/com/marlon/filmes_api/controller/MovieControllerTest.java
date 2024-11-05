package com.marlon.filmes_api.controller;

import com.marlon.filmes_api.entity.Movie;
import com.marlon.filmes_api.dtos.MovieResult;
import com.marlon.filmes_api.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class MovieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSave() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");

        when(movieService.save(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Movie"));

        verify(movieService, times(1)).save(any(Movie.class));
    }

    @Test
    void testGetIntervaloPremio() throws Exception {
        MovieResult movieResult = new MovieResult();
        when(movieService.getIntervaloPremio()).thenReturn(movieResult);

        mockMvc.perform(get("/movie/intervalopremio"))
                .andExpect(status().isOk());

        verify(movieService, times(1)).getIntervaloPremio();
    }

    @Test
    void testListMovie() throws Exception {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setId(1L);
        movie2.setId(2L);
        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieService.list()).thenReturn(movies);

        mockMvc.perform(get("/movie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));

        verify(movieService, times(1)).list();
    }
}