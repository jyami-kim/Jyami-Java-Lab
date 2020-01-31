package com.jyami.crawlermockservertest.dto;

import com.jyami.crawlermockservertest.runner.NaverMovieCrawlerTest;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.print.Doc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Created by jyami on 2020/01/31
 */
class TopMovieListTest {

    @Test
    void getTopMovies() {

        Document document = new NaverMovieCrawlerTest().getCrawlingResultWithFile("ranking_naver_movie.html");
        TopMovieList topMovieList = new TopMovieList(document);
        assertThat(topMovieList.getTopMovies().size()).isEqualTo(50);
    }
}