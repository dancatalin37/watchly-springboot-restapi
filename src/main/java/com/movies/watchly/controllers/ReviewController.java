package com.movies.watchly.controllers;

import com.movies.watchly.models.Movie;
import com.movies.watchly.models.Review;
import com.movies.watchly.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(path = "/reviews/movies/{movieId}")
    public ResponseEntity<List<Review>> getAllReviewsOfAMovie(@PathVariable Long movieId) {
        List<Review> reviews = reviewService.getAllReviewsByMovieId(movieId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping(path = "/reviews/movies/{movieId}")
    public ResponseEntity<Review> addReviewToMovie(@PathVariable Long movieId, @RequestBody Review review) {
        Review newReview = reviewService.addReviewToMovie(movieId, review);
        return new ResponseEntity<>(newReview, HttpStatus.OK);
    }

}
