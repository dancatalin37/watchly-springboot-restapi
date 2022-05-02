package com.movies.watchly.models.dtos;

import com.movies.watchly.enums.Awards;
import com.movies.watchly.models.Actor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AwardsDTO {

    private List<Awards> awards;

}
