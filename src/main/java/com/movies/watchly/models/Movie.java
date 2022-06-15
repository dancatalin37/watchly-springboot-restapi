package com.movies.watchly.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movies.watchly.enums.Genre;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
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