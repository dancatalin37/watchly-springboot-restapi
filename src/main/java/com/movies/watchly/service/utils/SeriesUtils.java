package com.movies.watchly.service.utils;

import com.movies.watchly.models.Season;

import java.util.List;

public final class SeriesUtils {

    public static int getSeriesDuration(List<Season> seaons){
        return seaons.stream().mapToInt(Season::getDuration).sum();
    }

}
