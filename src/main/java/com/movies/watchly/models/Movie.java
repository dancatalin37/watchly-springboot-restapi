package com.movies.watchly.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "movies")
public class Movie extends Video {

    @NotNull
    private int runtime;

    @Column (columnDefinition = "integer default 0")
    private int averageRating;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private List<Watchlist> watchlists = new ArrayList<>();

}