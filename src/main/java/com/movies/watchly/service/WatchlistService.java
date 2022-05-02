package com.movies.watchly.service;

import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Movie;
import com.movies.watchly.models.User;
import com.movies.watchly.models.Watchlist;
import com.movies.watchly.repository.MovieRepository;
import com.movies.watchly.repository.UserRepository;
import com.movies.watchly.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;

    private final UserRepository userRepository;

    private final MovieRepository movieRepository;

    /**
     * Gets the user watchlist
     * @return the user'ss watchlist
     */

    public Watchlist getWatchlistByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("User not found"));

        Watchlist watchlist = watchlistRepository.findByUser(user)
                .orElseThrow(() -> new ApiRequestException("Watchlist not found"));

        return watchlist;
    }

    /**
     * Adds a movie to a user's watchlist
     * If the watchlist does not exist, it creates a new instance of it
     * @param movieId - the ID of the movie to be added
     * @return the user's watchlist
     */

    public Watchlist addMovieToWatchlist(Long movieId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("User not found"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApiRequestException("Movie not found"));

        boolean existingWatchlist = watchlistRepository.findByUser(user).isPresent();

        if(existingWatchlist) {
            Watchlist watchlist = watchlistRepository.findByUser(user).get();
            if(!watchlist.getMovies().contains(movie)){
                watchlist.getMovies().add(movie);
                watchlistRepository.save(watchlist);
                return watchlist;
            }

        } else {
            Watchlist watchlist = new Watchlist();
            watchlist.getMovies().add(movie);
            watchlist.setUser(user);
            watchlistRepository.save(watchlist);
            return watchlist;
        }
       return watchlistRepository.findByUser(user).get();
    }

}
