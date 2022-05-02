package com.movies.watchly.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_watchlists",
            joinColumns = @JoinColumn(
                    name = "watchlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_id", referencedColumnName = "id"))
    private List<Movie> movies = new ArrayList<>();

}
