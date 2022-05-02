package com.movies.watchly.service;

import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Movie;
import com.movies.watchly.models.dtos.FilmographyDTO;
import com.movies.watchly.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * Gets all the movies from the database
     * @return a list of movies
     */

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Searches for a movie based of ID provided
     * @param id
     * @return the searched movie
     */

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->
                new ApiRequestException("Movie not found"));
    }

    /**
     * Adds a movie to the database
     * @param movie
     * @return the recently added movie
     */

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Deletes the movie from the database based on the ID provided
     * @param movieId
     */

    public void deleteMovie(Long movieId){
        movieRepository.findById(movieId).ifPresent(movieRepository::delete);
    }

    /**
     * Updates a given movie cast list.
     * @param movieId - the id of the movie to be updated
     * @param filmographyDTO - the list of actors to be added
     * @return the updated movie
     */

    public Movie updateMovieCastList(Long movieId, FilmographyDTO filmographyDTO) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()
                -> new ApiRequestException("Movie not found"));

        movie.setCast(filmographyDTO.getActors());
        movieRepository.save(movie);
        return movie;
    }

    /**
     * Gets all the movies sorted by ratings
     * @param limit the number of movies to be retrieved
     * @return a sorted list of movies based on the ratings
     */

    public List<Movie> getMoviesSortedByRatings(Long limit) {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().sorted(Comparator.comparing((Movie::getAverageRating))
                .reversed()).limit(limit).collect(Collectors.toList());
    }

    /**
     * Gets all the movies sorted by duration
     * @param limit the number of movies to be retrieved
     * @return a sorted list of movies based on the duration
     */

    public List<Movie> getMoviesSortedByDuration(Long limit) {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().sorted(Comparator.comparing((Movie::getRuntime))
                .reversed()).limit(limit).collect(Collectors.toList());
    }

}
