package com.movies.watchly.service.utils;

import com.movies.watchly.models.Movie;
import com.movies.watchly.models.Video;

import java.util.ArrayList;
import java.util.List;

public final class ActorUtils {

    public static int calculateMeanOfMoviesRatings(List<Video> filmography) {
        List<Movie> movies = new ArrayList<>();

        filmography.stream().filter((movie) -> movie instanceof Movie).forEach(
                (movie) -> {
                    if(((Movie) movie).getAverageRating() > 0) {
                        movies.add((Movie) movie);
                    }
                });

        double calculateTotalAverageRating =
                movies.stream().mapToDouble(Movie::getAverageRating).sum();

        return (int) calculateTotalAverageRating / filmography.size();
    }

}
