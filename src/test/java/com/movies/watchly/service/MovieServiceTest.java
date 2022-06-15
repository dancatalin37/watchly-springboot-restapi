package com.movies.watchly.service;

import com.movies.watchly.models.Movie;
import com.movies.watchly.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class MovieServiceTest {

    @Mock private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieService = new MovieService(movieRepository);
    }

   /* @Test
    void itShouldReturnMovieById(){
        Movie movie = new Movie(1L,
                "Interstellar",
                "Great SF Movie",
                LocalDate.now(),
                "Image",
                null,
                null,
                180,
                5,
                null,
                null
        );

        given(movieRepository.findById(1L)).willReturn(Optional.of(movie));

        Movie movieToBeFound = movieService.getMovieById(1L);

        assertThat(movieToBeFound).isEqualTo(movie);
    }*/

}