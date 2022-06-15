package com.movies.watchly.controllers;

import com.movies.watchly.models.Actor;
import com.movies.watchly.models.Movie;
import com.movies.watchly.models.Video;
import com.movies.watchly.models.dtos.FilmographyDTO;
import com.movies.watchly.security.RabbitMQConfig;
import com.movies.watchly.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class MovieController {

    private final MovieService movieService;

    private final RabbitTemplate rabbitTemplate;

    @GetMapping(path = "/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        List<String> movieNames = movies.stream().map(Video::getTitle).collect(Collectors.toList());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, movieNames);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/movies/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping(path = "/movies/ratings")
    public ResponseEntity<List<Movie>> getMoviesSortedByRatings(@RequestParam Long limit) {
        List<Movie> movies = movieService.getMoviesSortedByRatings(limit);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/movies/runtime")
    public ResponseEntity<List<Movie>> getMoviesBasedOnRuntime(@RequestParam Long limit) {
        List<Movie> movies = movieService.getMoviesSortedByDuration(limit);
        return new ResponseEntity<>(movies, HttpStatus.OK); // ok
    }


    @PostMapping(path = "/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie addedMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(addedMovie, HttpStatus.OK);
    }

    @DeleteMapping(path = "/movies/{movieId}")
    public ResponseEntity<String> deleteMovieById(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Movie deleted succesfully", HttpStatus.OK);
    }

    @PatchMapping(path = "/movies/{movieId}")
    public ResponseEntity<Movie> updateMovieCast(@PathVariable Long movieId,
                                                 @RequestBody FilmographyDTO filmographyDTO) {
        Movie movie = movieService.updateMovieCastList(movieId, filmographyDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

}