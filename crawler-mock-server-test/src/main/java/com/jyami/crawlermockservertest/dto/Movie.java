package com.jyami.crawlermockservertest.dto;

import lombok.Builder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by jyami on 2020/01/30
 */

public class Movie {
    private int rank;
    private String title;
    private String detailLink;

    @Builder
    private Movie(int rank, String title, String detailLink) {
        this.rank = rank;
        this.title = title;
        this.detailLink = detailLink;
    }

    public static Movie of (Element element){
        return Movie.builder()
                .rank(Integer.parseInt(element.select(".ac img").attr("alt")))
                .title(element.select(".title").text())
                .detailLink(element.select(".title a").attr("href"))
                .build();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rank='" + rank + '\'' +
                ", title='" + title + '\'' +
                ", detailLink='" + detailLink + '\'' +
                '}';
    }
}
