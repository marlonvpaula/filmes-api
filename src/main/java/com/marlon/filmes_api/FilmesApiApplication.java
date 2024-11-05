package com.marlon.filmes_api;

import com.marlon.filmes_api.entity.Movie;
import com.marlon.filmes_api.service.MovieService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;

@SpringBootApplication
public class FilmesApiApplication implements CommandLineRunner {

	@Autowired
	private MovieService movieService;

	public static void main(String[] args) {
        SpringApplication.run(FilmesApiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		movieService.saveInicial();
	}


}
