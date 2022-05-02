package com.movies.watchly.service;

import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Actor;
import com.movies.watchly.models.Movie;
import com.movies.watchly.models.dtos.AwardsDTO;
import com.movies.watchly.models.dtos.DescriptionDTO;
import com.movies.watchly.repository.ActorRepository;
import com.movies.watchly.repository.MovieRepository;
import com.movies.watchly.service.utils.ActorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    private final MovieRepository movieRepository;

    /**
     * Gets all the actors from the database
     * @return a list of actors
     */

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    /**
     * Searches for an actor based of ID provided
     * @param id
     * @return the searched actor
     */

    public Actor getActorById(Long id) {
        return actorRepository.findById(id).orElseThrow(()
                -> new ApiRequestException("Actor not found"));
    }

    /**
     * Adds an actor to the database
     * @param actor
     * @return the recently added actor
     */

    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    /**
     * Deletes the actor from the database based on the ID provided
     * @param id
     */

    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(()
                -> new ApiRequestException("Actor not found"));
        actorRepository.delete(actor);
    }

    /**
     * Searches the database for all the movies an actor starred in
     * @param actorId
     * @return a list of actors
     */

    public List<Movie> getAllMoviesAnActorStarredIn(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow(() ->
                new ApiRequestException("Actor not found"));

        List<Movie> movies = movieRepository.findAll();

        return movies.stream().filter((movie) -> movie.getCast().contains(actor)).collect(Collectors.toList());
    }

    /**
     * Sorts the actors from the database based of the average rating from all
     * the movies they starred in
     * @param numberOfActors - the number of actors to be returned
     * @return a list of actors
     */

    public List<Actor> getActorsBasedOfMovieMeanRatings(Long numberOfActors) {
        List<Actor> actors = actorRepository.findAll();

        return actors.stream().sorted(Comparator.comparing(
                        (Actor actor) -> ActorUtils.calculateMeanOfMoviesRatings(actor.getFilmography()))
                        .reversed())
                        .limit(numberOfActors).collect(Collectors.toList());
    }

    /**
     * Searched the database for all the actors that were given the all awards
     * provided in the list
     * @param awardsDTO - a list containing the awards an actor must have
     * @return a list of actors
     */

    public List<Actor> getActorsBasedOfAwardsGiven(AwardsDTO awardsDTO) {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream().filter((actor) -> actor.getAwards().containsAll(awardsDTO.getAwards()))
                .sorted(Comparator.comparing((Actor actor) ->
                        actor.getAwards().size()).reversed()).collect(Collectors.toList());
    }

    /**
     * Searches the list of actors from the database that have in their description
     * all the words provided in the list
     * @param descriptionDTO - the list of keywords
     * @return a list of actors
     */

    public List<Actor> getActorBasedOfDescription(DescriptionDTO descriptionDTO) {
        List<Actor> actors = actorRepository.findAll();
        List<Actor> filteredActorsBasedOfDescription = new ArrayList<>();
        for (Actor actor : actors) {
            int ok = 1;
            for (String word : descriptionDTO.getContent()) {
                if (!actor.getBiography().contains(word)) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                filteredActorsBasedOfDescription.add(actor);
            }
        }
        return filteredActorsBasedOfDescription;
    }

}

