package com.jyami.crawlermockservertest.runner;

import com.jyami.crawlermockservertest.dto.TopMovieList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jyami on 2020/01/30
 */
public class NaverMovieCrawler {

    public static void main(String[] args) {
        Document document = new NaverMovieCrawler().getCrawlingResult("https://movie.naver.com/movie/sdb/rank/rmovie.nhn");
        TopMovieList topMovieList = new TopMovieList(document);
        System.out.println(topMovieList.toString());
    }

    // url 을 보내면, 해당 페이지를 크롤링하여 Document 타입을 리턴한다.
    public Document getCrawlingResult(String url){
        try {
            return Jsoup.connect(url)
                    .timeout(2000)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException("crawling 실패");
        }
    }
}
