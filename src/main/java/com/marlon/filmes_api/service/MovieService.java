package com.marlon.filmes_api.service;

import com.marlon.filmes_api.FilmesApiApplication;
import com.marlon.filmes_api.dtos.MovieDto;
import com.marlon.filmes_api.entity.Movie;
import com.marlon.filmes_api.dtos.MovieResult;
import com.marlon.filmes_api.repository.MovieRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public record MovieService (MovieRepository movieRepository) {

    public Movie save (Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> list () {
        return movieRepository.findAll();
    }

    public MovieResult getIntervaloPremio () {
        List<MovieDto> list = movieRepository.findProducerIntervals();
        MovieResult result = new MovieResult();
        List<MovieDto> listMin = new ArrayList<>();
        List<MovieDto> listMax = new ArrayList<>();
        listMin.add(list.get(0));
        listMin.add(list.get(1));
        listMax.add(list.get(list.size()-1));
        listMax.add(list.get(list.size()-2));

        result.setMax(listMax);
        result.setMin(listMin);
        return result;
    }

    public void saveInicial () {

        try (Reader reader = new InputStreamReader(FilmesApiApplication.class.getResourceAsStream( "/static/movielist.csv"));
             CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {

            // Pula o cabeçalho
            String[] nextLine = csvReader.readNext();

            movieRepository.deleteAll();

            // Itera sobre as linhas e insere os dados na tabela
            while ((nextLine = csvReader.readNext()) != null) {
                Movie movie = new Movie();
                movie.setYearMovie(Long.parseLong(nextLine[0]));
                movie.setTitle(nextLine[1]);
                movie.setStudios(nextLine[2]);
                movie.setProducers(nextLine[3]);
                movie.setWinner(nextLine[4]);

                save(movie);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
