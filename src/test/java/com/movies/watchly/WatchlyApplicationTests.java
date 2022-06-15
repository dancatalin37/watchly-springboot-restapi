package com.movies.watchly;

import com.movies.watchly.models.Movie;
import com.movies.watchly.repository.MovieRepository;
import com.movies.watchly.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
class WatchlyApplicationTests {


	@Autowired
	private MovieService movieService;

	@MockBean
	private MovieRepository movieRepository;

	@Test
	void shouldReturnAnEmptyList(){
		given(movieRepository.findAll()).willReturn(Collections.emptyList());

		List<Movie> emptyList = movieService.getAllMovies();

		assertTrue(emptyList.isEmpty());
	}

//	@Test
//	void shouldReturnASingleMovieById(){
//		given(movieRepository.findById(1L)).willReturn(Optional.of(movie));
//
//	}

}
