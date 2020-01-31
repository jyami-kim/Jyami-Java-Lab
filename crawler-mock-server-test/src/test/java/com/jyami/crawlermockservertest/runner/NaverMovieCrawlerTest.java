package com.jyami.crawlermockservertest.runner;

import com.jyami.crawlermockservertest.dto.TopMovieList;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by jyami on 2020/01/31
 */
public class NaverMovieCrawlerTest {


    private static final int PORT = 9000;
    private static ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(PORT);
        System.out.println("mock server start");
    }

    @Test
    public void naverMovieMockServerTest(){

        createNaverRankingPageServer("ranking_naver_movie.html");
        Document document = new NaverMovieCrawler().getCrawlingResult("http://localhost:9000/movie/sdb/rank/rmovie.nhn");
        TopMovieList topMovieList = new TopMovieList(document);
        System.out.println("assert Test");
        assertThat(topMovieList.getTopMovies().size()).isEqualTo(50);

    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
        System.out.println("mock server stop");
    }

    private byte[] readHtmlFile(String filePath) {
        InputStream resourceAsStream = getClass().getClassLoader()
                .getResourceAsStream(filePath);
        try {
            assert resourceAsStream != null;
            return IOUtils.toByteArray(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("file IO 실패");
        }
    }

    private void createNaverRankingPageServer(String filePath){
        byte[] response = readHtmlFile(filePath);

        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/movie/sdb/rank/rmovie.nhn")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(response)
                );
    }

}