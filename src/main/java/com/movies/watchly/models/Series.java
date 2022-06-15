package com.movies.watchly.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Series extends Video {

    @OneToMany(mappedBy = "series")
    private List<Season> seasons = new ArrayList<>();

}