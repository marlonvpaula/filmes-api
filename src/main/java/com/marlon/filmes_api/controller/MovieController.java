package com.marlon.filmes_api.controller;

import com.marlon.filmes_api.entity.Movie;
import com.marlon.filmes_api.dtos.MovieResult;
import com.marlon.filmes_api.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public record MovieController (MovieService movieService) {

    @PostMapping
    public Movie save (@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @GetMapping("intervalopremio")
    public MovieResult getIntervaloPremio () {
        return movieService.getIntervaloPremio();
    }

    @GetMapping()
    public List<Movie> listMovie () {
        return movieService.list();
    }
}
