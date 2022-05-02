package com.movies.watchly.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.movies.watchly.enums.Genre;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "movie"),
        @JsonSubTypes.Type(value = Series.class, name = "series")
})
public abstract class Video {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "video_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Actor> cast = new ArrayList<>();

    @ElementCollection(targetClass = Genre.class)
    @JoinTable(name = "genres", joinColumns = @JoinColumn(name = "genreId"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres = new ArrayList<>();

}