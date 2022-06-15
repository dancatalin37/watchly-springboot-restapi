package com.movies.watchly.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.movies.watchly.enums.Awards;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
@Getter
@Setter
@RequiredArgsConstructor
public class Actor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    @Past
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty
    private String biography;

    @ManyToMany(mappedBy = "cast")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Video> filmography = new ArrayList<>();

    @ElementCollection(targetClass = Awards.class)
    @JoinTable(name = "awards", joinColumns = @JoinColumn(name = "actorId"))
    @Column(name = "award", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Awards> awards = new ArrayList<>();

}