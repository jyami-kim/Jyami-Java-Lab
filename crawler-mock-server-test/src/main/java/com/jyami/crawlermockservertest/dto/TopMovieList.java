package com.jyami.crawlermockservertest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/01/30
 */

@AllArgsConstructor
@Getter
public class TopMovieList {

    private List<Movie> topMovies = new ArrayList<>();

    public TopMovieList(Document document){
        this.topMovies = document.select("table.list_ranking td").stream()
                .map(Movie::of)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return topMovies.stream()
                .map(Movie::toString)
                .collect(Collectors.joining(", \n"));
    }
}
