package com.jyami.crawlermockservertest.dto;

import com.jyami.crawlermockservertest.runner.NaverMovieCrawlerTest;
import org.jsoup.nodes.Document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static com.jyami.crawlermockservertest.util.testUtil.getCrawlingResultWithFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Created by jyami on 2020/01/31
 */
class TopMovieListTest {

    Document document;

    @BeforeEach
    void setUp() {
        document = getCrawlingResultWithFile("ranking_naver_movie.html");
    }

    @Test
    void getTopMovies() {
        TopMovieList topMovieList = new TopMovieList(document);
        assertThat(topMovieList.getTopMovies().size()).isEqualTo(50);
    }

}