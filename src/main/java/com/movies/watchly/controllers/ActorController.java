package com.movies.watchly.controllers;

import com.movies.watchly.enums.Awards;
import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Actor;
import com.movies.watchly.models.Movie;
import com.movies.watchly.models.Video;
import com.movies.watchly.models.dtos.AwardsDTO;
import com.movies.watchly.models.dtos.DescriptionDTO;
import com.movies.watchly.models.dtos.FilmographyDTO;
import com.movies.watchly.service.ActorService;
import com.movies.watchly.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class ActorController {

    private final ActorService actorService;

    @GetMapping(path = "/actors")
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping(path = "/actors/{actorId}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long actorId){
        Actor searchedActor = actorService.getActorById(actorId);
        return new ResponseEntity<>(searchedActor, HttpStatus.OK);
    }

    @GetMapping(path = "/actors/rankings")
    public ResponseEntity<List<Actor>> getActorsByRatings(@RequestParam Long limit) {
        List<Actor> actors = actorService.getActorsBasedOfMovieMeanRatings(limit);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping(path = "/actors/{actorId}/movies")
    public ResponseEntity<List<Movie>> getActorsMovies(@PathVariable Long actorId) {
        List<Movie> actorFilmography = actorService.getAllMoviesAnActorStarredIn(actorId);
        return new ResponseEntity<>(actorFilmography, HttpStatus.OK);
    }

    @PostMapping(path = "/actors")
    public ResponseEntity<Actor> addActor(@Valid @RequestBody Actor actor){
        Actor newlyAddedActor = actorService.addActor(actor);
        return new ResponseEntity<>(newlyAddedActor, HttpStatus.OK);
    }

    @DeleteMapping(path = "/actors/{actorId}")
    public ResponseEntity<String> deleteActor(@PathVariable Long actorId){
        actorService.deleteActor(actorId);
        return new ResponseEntity<>("Actor deleted succesfully", HttpStatus.OK);
    }

    @GetMapping(path = "/actors/awards")
    public ResponseEntity<List<Actor>> getActorsBasedOfAwards(@RequestBody AwardsDTO awardsDTO){
        List<Actor> actors = actorService.getActorsBasedOfAwardsGiven(awardsDTO);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping(path = "/actors/description")
    public ResponseEntity<List<Actor>> getActorBasedOfKeywords(@RequestBody DescriptionDTO descriptionDTO){
        List<Actor> actors = actorService.getActorBasedOfDescription(descriptionDTO);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }


}
