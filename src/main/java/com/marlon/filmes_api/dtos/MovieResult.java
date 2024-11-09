package com.marlon.filmes_api.dtos;

import com.marlon.filmes_api.entity.Movie;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;


public class MovieResult {


    private List<MovieDto> min;

    private List<MovieDto> max;


    public MovieResult() {
        this.min = new ArrayList<>();
        this.max = new ArrayList<>();
    }

    public List<MovieDto> getMin() {
        return min;
    }

    public void setMin(List<MovieDto> min) {
        this.min = min;
    }

    public List<MovieDto> getMax() {
        return max;
    }

    public void setMax(List<MovieDto> max) {
        this.max = max;
    }
}
