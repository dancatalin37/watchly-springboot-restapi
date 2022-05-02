package com.movies.watchly.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private User user;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Movie movie;

    @NotEmpty
    private String title;

    @NotEmpty
    private String comment;

    @NotNull
    @Range (min = 1, max = 5)
    private int grade;

    @NotNull
    private LocalDateTime reviewedAt;

}