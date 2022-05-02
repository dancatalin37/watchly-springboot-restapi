package com.movies.watchly.repository;

import com.movies.watchly.models.Movie;
import com.movies.watchly.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findAllByMovie(Movie movie);
}
