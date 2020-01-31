package com.jyami.crawlermockservertest.dto;

import com.jyami.crawlermockservertest.runner.NaverMovieCrawlerTest;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.jyami.crawlermockservertest.util.testUtil.getCrawlingResultWithFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/01/31
 */
class MovieTest {

    Element element;

    @BeforeEach
    void setUp() {
        element = getCrawlingResultWithFile("ranking_1st_naver_movie.html").body();
    }

    @Test
    void of() {
        Movie movie = Movie.of(element);
        assertThat(movie.getDetailLink()).isEqualTo("https://movie.naver.com/movie/bi/mi/basic.nhn?code=176306");
        assertThat(movie.getTitle()).isEqualTo("남산의 부장들");
        assertThat(movie.getRank()).isEqualTo(1);
    }

}