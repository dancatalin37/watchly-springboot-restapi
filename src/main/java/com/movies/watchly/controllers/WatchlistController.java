package com.movies.watchly.controllers;

import com.movies.watchly.models.Watchlist;
import com.movies.watchly.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @GetMapping("/watchlist")
    public ResponseEntity<Watchlist> getUserWatchlist(){
        Watchlist watchlist = watchlistService.getWatchlistByUser();
        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @PostMapping("/watchlist/movies/{movieId}")
    public ResponseEntity<Watchlist> addMovieToWatchlist(@PathVariable Long movieId){
        Watchlist watchlist = watchlistService.addMovieToWatchlist(movieId);
        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

}
