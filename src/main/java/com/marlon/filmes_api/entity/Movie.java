package com.marlon.filmes_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long yearMovie;

    @Column
    private String title;

    @Column
    private String studios;

    @Column
    private String producers;

    @Column
    private String winner;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYearMovie() {
        return yearMovie;
    }

    public void setYearMovie(Long yearMovie) {
        this.yearMovie = yearMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "yearMovie=" + yearMovie +
                ", title='" + title + '\'' +
                '}';
    }
}
