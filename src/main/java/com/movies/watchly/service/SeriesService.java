package com.movies.watchly.service;

import com.movies.watchly.exceptions.ApiRequestException;
import com.movies.watchly.models.Season;
import com.movies.watchly.models.Series;
import com.movies.watchly.repository.SeasonRepository;
import com.movies.watchly.repository.SeriesRepository;
import com.movies.watchly.service.utils.SeriesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final SeasonRepository seasonRepository;

    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    public Series getSeriesbyId(Long seriesId) {
        return seriesRepository.findById(seriesId)
                .orElseThrow(() ->
                        new ApiRequestException("Series not found"));
    }

    public List<Series> sortSeriesByTheirDuration(Long number){
        List<Series> series = seriesRepository.findAll();
        return series.stream().sorted(Comparator.comparing((Series singleSeries) ->
                        SeriesUtils.getSeriesDuration(singleSeries.getSeasons())
                )).limit(number).collect(Collectors.toList());

    }

}
