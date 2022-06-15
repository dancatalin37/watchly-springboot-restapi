package com.movies.watchly.service;

import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Movie;
import com.movies.watchly.models.Review;
import com.movies.watchly.models.User;
import com.movies.watchly.models.Video;
import com.movies.watchly.repository.MovieRepository;
import com.movies.watchly.repository.ReviewRepository;
import com.movies.watchly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final MovieRepository movieRepository;

    private final UserRepository userRepository;

    /**
     * Returns all the reviews of a movie
     * @param movieId - the ID of the movie to return the reviews
     * @return a list of reviews for the specified movie
     */

    public List<Review> getAllReviewsByMovieId(Long movieId){
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApiRequestException("Movie not found"));

        return reviewRepository.findAllByMovie(movie)
                .orElseThrow(() -> new ApiRequestException("No reviews found"));
    }

    /**
     * Adds a review to an existing movie
     * @param movieId - the ID of the movie to be reviewed
     * @param review - the body of the review to be added
     * @return the added review
     */

    public Review addReviewToMovie(Long movieId, Review review) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApiRequestException("Movie not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("User not found"));

        int sumOfTotalRatings = movie.getReviews().stream().mapToInt(Review::getGrade).sum();
        sumOfTotalRatings += review.getGrade();
        int numberOfReviews = movie.getReviews().size() + 1;

        int averageRating = sumOfTotalRatings / numberOfReviews;

        movie.setAverageRating(averageRating);
        movieRepository.save(movie);

        review.setMovie(movie);
        review.setUser(user);
        review.setReviewedAt(LocalDateTime.now());
        reviewRepository.save(review);

        return review;
    }

}
