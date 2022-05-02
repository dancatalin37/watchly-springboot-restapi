package com.movies.watchly.repository;

import com.movies.watchly.models.User;
import com.movies.watchly.models.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {

    Optional<Watchlist> findByUser(User user);

}
