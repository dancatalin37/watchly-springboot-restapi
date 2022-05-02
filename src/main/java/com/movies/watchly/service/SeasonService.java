package com.movies.watchly.service;

import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Season;
import com.movies.watchly.models.Series;
import com.movies.watchly.repository.SeasonRepository;
import com.movies.watchly.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

    private final SeriesRepository seriesRepository;

    public List<Season> getAllSeasonsOfASeries(Long seriesId){
        List<Season> seasons = seasonRepository.findAll();
        return seasons.stream().filter((season) ->
                season.getSeries().getId().equals(seriesId)).collect(Collectors.toList());

    }

}
